// --------------------- Variables globales ---------------------
let userRole = null;       // Rol real del usuario logueado
let userEmail = null;      // Email del usuario logueado
let solicitudes = [];      // Lista de solicitudes
let roles = [];            // Lista de roles disponibles
const app = document.getElementById("app");
let currentSection = "solicitudes";

// --------------------- Funciones API ---------------------
async function cargarRoles() {
    try {
        const response = await fetch("http://localhost:8080/api/seguridad/listarRol");
        roles = await response.json();
        console.log("Roles cargados:", roles);
    } catch (error) {
        console.error("Error al cargar roles:", error);
    }
}

async function cargarUsuario() {
    try {
        if (!userEmail) return;

        // Validar que el usuario existe
        const responseExiste = await fetch("http://localhost:8080/api/seguridad/existeUsuario", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ loginUsuario: userEmail })
        });

        if (!responseExiste.ok) {
            console.error("Error al verificar usuario:", responseExiste.status);
            return;
        }

        const usuarioData = await responseExiste.json();
        console.log("Usuario logueado:", usuarioData);

        // Obtener menú/rol del usuario
        const responseMenu = await fetch("http://localhost:8080/api/seguridad/obtieneMenu", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ loginUsuario: userEmail })
        });

        if (!responseMenu.ok) {
            console.error("Error al obtener menú:", responseMenu.status);
            return;
        }

        const menuData = await responseMenu.json();
        console.log("Opciones de menú:", menuData);

        // Asumimos que el backend devuelve el rol dentro de la primera opción del menú
        if (Array.isArray(menuData) && menuData.length > 0) {
            userRole = menuData[0].nombreRol?.toUpperCase() || null;
        } else {
            userRole = null;
        }

        sessionStorage.setItem("userRole", userRole);

    } catch (error) {
        console.error("Error al cargar usuario:", error);
    }
}


async function cargarSolicitudes() {
    try {
        const response = await fetch("http://localhost:8080/api/solicitudes");
        if (!response.ok) throw new Error("No se pudo cargar solicitudes");
        solicitudes = await response.json();
        console.log("Solicitudes cargadas:", solicitudes);
    } catch (error) {
        console.error("Error al cargar solicitudes:", error);
        solicitudes = [];
    }
}

// --------------------- Sidebar ---------------------
function showSection(section) {
    currentSection = section;
    renderApp();
}

function cerrarSesion() {
    sessionStorage.clear();
    window.location.href = "../inicio-de-sesion/index.html";
}

// --------------------- Render principal ---------------------
function renderApp() {
    app.innerHTML = "";
    if (!userRole) return;

    if (currentSection === "solicitudes") {
        switch(userRole) {
            case "COMUNIDAD": renderUsuario(); break;
            case "COMITE": renderComite(); break;
            case "ADMINISTRADOR": renderAdmin(); break;
            default: console.warn("Rol desconocido:", userRole); break;
        }
    } else if (currentSection === "grafico") {
        renderGrafico();
    }
}

// --------------------- Función común para agregar solicitud ---------------------
async function agregarSolicitud(form) {
    const titulo = form.querySelector('input[name="titulo"]').value.trim();
    const descripcion = form.querySelector('textarea[name="descripcion"]').value.trim();
    const emailUsuario = form.querySelector('input[name="emailUsuario"]')?.value.trim() || userEmail;
    if (!titulo || !descripcion) return;

    try {
        const response = await fetch("http://localhost:8080/api/solicitudes", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ titulo, descripcion, estado: "pendiente", emailUsuario })
        });
        const nueva = await response.json();
        solicitudes.push(nueva);
        renderApp();
        alert("Solicitud enviada correctamente");
    } catch (error) {
        console.error("Error al agregar solicitud:", error);
    }
}

// --------------------- Render Usuario ---------------------
function renderUsuario() {
    const div = document.createElement("div");
    div.id = "usuario-section";
    div.innerHTML = `<h2>Mis Solicitudes</h2>`;

    const form = document.createElement("form");
    form.id = "usuario-form";
    form.innerHTML = `
        <input type="text" name="titulo" placeholder="Título" required>
        <textarea name="descripcion" placeholder="Descripción breve" required></textarea>
        <button type="submit">Enviar Solicitud</button>
    `;
    form.onsubmit = async e => { e.preventDefault(); await agregarSolicitud(form); };
    div.appendChild(form);

    solicitudes.forEach(s => {
        if (!s.emailUsuario || s.emailUsuario.toLowerCase() !== userEmail.toLowerCase()) return;

        const card = document.createElement("div");
        card.className = "card";
        card.id = `solicitud-${s.id}`;
        card.innerHTML = `
            <h3>${s.titulo}</h3>
            <p>${s.descripcion}</p>
            <p>Estado: <span class="badge ${s.estado}">${s.estado}</span></p>
            ${s.estado === "completada" ? `<div class="stars" data-id="${s.id}" id="stars-${s.id}"></div>` : ""}
        `;
        div.appendChild(card);
    });

    const graficoDiv = document.createElement("div");
    graficoDiv.innerHTML = `<h3>Gráfico de Mis Solicitudes</h3><canvas id="chartUsuario" height="150"></canvas>`;
    div.appendChild(graficoDiv);

    app.appendChild(div);

    renderStars();
    renderGraficoUsuario();
}

// --------------------- Render Comité ---------------------
function renderComite() {
    const div = document.createElement("div");
    div.id = "comite-section";
    div.innerHTML = `<h2>Solicitudes a gestionar</h2>`;

    solicitudes.forEach(s => {
        const card = document.createElement("div");
        card.className = "card";
        card.id = `solicitud-${s.id}`;
        card.innerHTML = `
            <h3>${s.titulo}</h3>
            <p>${s.descripcion}</p>
            <p>Estado: <span class="badge ${s.estado}">${s.estado}</span></p>
            ${s.estado === "pendiente" ? `<button onclick="completarSolicitud(${s.id})">Marcar como Completada</button>` : ""}
        `;
        div.appendChild(card);
    });

    app.appendChild(div);
}

// --------------------- Render Administrador ---------------------
function renderAdmin() {
    const div = document.createElement("div");
    div.id = "admin-section";
    div.innerHTML = `<h2>Administrador - Control Total</h2>`;

    // Formulario para crear nueva solicitud
    const form = document.createElement("form");
    form.id = "admin-form";
    form.innerHTML = `
        <h3>Crear Nueva Solicitud</h3>
        <input type="text" name="titulo" placeholder="Título" required>
        <textarea name="descripcion" placeholder="Descripción breve" required></textarea>
        <input type="email" name="emailUsuario" placeholder="Email del solicitante" value="${userEmail}" required>
        <button type="submit">Enviar Solicitud</button>
    `;
    form.onsubmit = async e => { e.preventDefault(); await agregarSolicitud(form); };
    div.appendChild(form);

    solicitudes.forEach(s => {
        const card = document.createElement("div");
        card.className = "card";
        card.id = `solicitud-${s.id}`;
        card.innerHTML = `
            <h3>Título: <input type="text" value="${s.titulo}" id="titulo-${s.id}"></h3>
            <p>Descripción: <textarea id="desc-${s.id}">${s.descripcion}</textarea></p>
            <p>Estado: 
                <select id="estado-${s.id}">
                    <option value="pendiente" ${s.estado==="pendiente"?"selected":""}>Pendiente</option>
                    <option value="completada" ${s.estado==="completada"?"selected":""}>Completada</option>
                </select>
                <span class="badge ${s.estado}">${s.estado}</span>
            </p>
            <p>Calificación: ${s.calificacion || "No calificada"}</p>
            <button onclick="guardarCambios(${s.id})">Guardar Cambios</button>
            <button onclick="borrarSolicitud(${s.id})" style="background-color:#e74c3c">Borrar Solicitud</button>
        `;
        div.appendChild(card);
    });

    app.appendChild(div);
}

// --------------------- Completar Solicitud ---------------------
async function completarSolicitud(id) {
    const descripcion = prompt("Describe lo que se hizo:");
    if (!descripcion) return;

    try {
        const response = await fetch(`http://localhost:8080/api/solicitudes/${id}/completar`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ descripcion })
        });
        const updated = await response.json();
        solicitudes = solicitudes.map(s => s.id === id ? updated : s);
        renderApp();
    } catch (error) {
        console.error("Error al completar solicitud:", error);
    }
}

// --------------------- Calificación ---------------------
function renderStars() {
    document.querySelectorAll(".stars").forEach(div => {
        const id = div.getAttribute("data-id");
        div.innerHTML = "";
        for (let i = 1; i <= 5; i++) {
            const span = document.createElement("span");
            const s = solicitudes.find(s => s.id == id);
            span.textContent = "★";
            span.className = s.calificacion >= i ? "active" : "";
            span.onclick = async () => {
                s.calificacion = i;
                try {
                    await fetch(`http://localhost:8080/api/solicitudes/${id}/calificacion`, {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify({ calificacion: i })
                    });
                    renderApp();
                } catch (error) {
                    console.error("Error al calificar:", error);
                }
            };
            div.appendChild(span);
        }
    });
}

// --------------------- Render gráfico global ---------------------
function renderGrafico() {
    app.innerHTML = `<h2>Gráfico de Solicitudes</h2><canvas id="chartSolicitudes" height="150"></canvas>`;

    const pendientes = solicitudes.filter(s => s.estado === "pendiente").length;
    const completadas = solicitudes.filter(s => s.estado === "completada").length;

    const ctx = document.getElementById('chartSolicitudes').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Pendientes', 'Completadas'],
            datasets: [{ label: 'Cantidad', data: [pendientes, completadas], backgroundColor: ['#3498db','#2ecc71'] }]
        },
        options: { responsive: true, plugins: { legend: { display: false } } }
    });
}

// --------------------- Render gráfico usuario ---------------------
function renderGraficoUsuario() {
    const canvas = document.getElementById('chartUsuario');
    if (!canvas) return;

    const pendientes = solicitudes.filter(s => s.estado === "pendiente" && s.emailUsuario.toLowerCase() === userEmail.toLowerCase()).length;
    const completadas = solicitudes.filter(s => s.estado === "completada" && s.emailUsuario.toLowerCase() === userEmail.toLowerCase()).length;

    const ctx = canvas.getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Pendientes', 'Completadas'],
            datasets: [{ label: 'Cantidad', data: [pendientes, completadas], backgroundColor: ['#3498db','#2ecc71'] }]
        },
        options: { responsive: true, plugins: { legend: { display: false } } }
    });
}

// --------------------- Inicialización ---------------------
document.addEventListener("DOMContentLoaded", async () => {
    userEmail = sessionStorage.getItem("userEmail");
    if (!userEmail) {
        window.location.href = "../inicio-de-sesion/index.html";
        return;
    }

    await cargarRoles();
    await cargarUsuario();

    if (!userRole) {
        console.error("No se pudo determinar el rol del usuario");
        return;
    }

    await cargarSolicitudes();
    renderApp();
});
