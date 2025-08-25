// Simulación de usuario logueado
const userRole = "usuario"; // Cambia a "usuario" o "comite" para probar otros roles

// Cargar solicitudes
let solicitudes = JSON.parse(localStorage.getItem("solicitudes")) || [
    { id: 1, titulo: "Bache en calle", descripcion: "Hay un bache", estado: "pendiente", calificacion: null },
    { id: 2, titulo: "Luminaria rota", descripcion: "No hay luz", estado: "completada", calificacion: 4 },
];

const app = document.getElementById("app");
let currentSection = 'solicitudes';

// Guardar en localStorage
function saveStorage() { localStorage.setItem("solicitudes", JSON.stringify(solicitudes)); }

// Sidebar
function showSection(section) { currentSection = section; renderApp(); }
function cerrarSesion() { alert("Sesión cerrada"); }

// Render principal
function renderApp() {
    app.innerHTML = "";
    if (currentSection === 'solicitudes') {
        if (userRole === "usuario") renderUsuario();
        if (userRole === "comite") renderComite();
        if (userRole === "admin") renderAdmin();
    }
    if (currentSection === 'grafico') renderGrafico();
}

// --- Usuario ---
function renderUsuario() {
    const div = document.createElement("div");
    div.innerHTML = `<h2>Mis Solicitudes</h2>`;

    const form = document.createElement("form");
    form.innerHTML = `
    <input type="text" name="titulo" placeholder="Título" required>
    <textarea name="descripcion" placeholder="Descripción breve" required></textarea>
    <button type="submit">Enviar Solicitud</button>
    `;
    form.onsubmit = e => { e.preventDefault(); agregarSolicitud(form); };
    div.appendChild(form);

    solicitudes.forEach(s => {
        const card = document.createElement("div");
        card.className = "card";
        card.innerHTML = `
    <h3>${s.titulo}</h3>
    <p>${s.descripcion}</p>
    <p>Estado: <span class="badge ${s.estado}">${s.estado}</span></p>
    ${s.estado === "completada" ? `<div class="stars" data-id="${s.id}"></div>` : ""}
    `;
        div.appendChild(card);
    });

    app.appendChild(div);
    renderStars();
}

function agregarSolicitud(form) {
    const titulo = form.querySelector('input[name="titulo"]').value.trim();
    const descripcion = form.querySelector('textarea[name="descripcion"]').value.trim();
    if (titulo && descripcion) {
        solicitudes.push({ id: Date.now(), titulo, descripcion, estado: "pendiente", calificacion: null });
        saveStorage(); renderApp();
    }
}

function renderStars() {
    document.querySelectorAll(".stars").forEach(div => {
        const id = div.getAttribute("data-id");
        div.innerHTML = "";
        for (let i = 1; i <= 5; i++) {
            const span = document.createElement("span");
            span.textContent = "★";
            span.className = solicitudes.find(s => s.id == id).calificacion >= i ? "active" : "";
            span.onclick = () => { solicitudes.find(s => s.id == id).calificacion = i; saveStorage(); renderApp(); };
            div.appendChild(span);
        }
    });
}

// --- Comité ---
function renderComite() {
    const div = document.createElement("div");
    div.innerHTML = `<h2>Solicitudes a gestionar</h2>`;
    solicitudes.forEach(s => {
        const card = document.createElement("div");
        card.className = "card";
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

function completarSolicitud(id) {
    const descripcion = prompt("Describe lo que se hizo:");
    if (descripcion) {
        const s = solicitudes.find(s => s.id === id);
        s.estado = "completada";
        s.descripcion += " | " + descripcion;
        saveStorage(); renderApp();
    }
}

// --- Administrador ---
function renderAdmin() {
    const div = document.createElement("div");
    div.innerHTML = `<h2>Administrador - Control Total</h2>`;

    solicitudes.forEach(s => {
        const card = document.createElement("div");
        card.className = "card";

        const estadoOptions = `
    <select id="estado-${s.id}">
        <option value="pendiente" ${s.estado === "pendiente" ? "selected" : ""}>Pendiente</option>
        <option value="completada" ${s.estado === "completada" ? "selected" : ""}>Completada</option>
    </select>
    `;

        card.innerHTML = `
    <h3>Título: <input type="text" value="${s.titulo}" id="titulo-${s.id}"></h3>
    <p>Descripción: <textarea id="desc-${s.id}">${s.descripcion}</textarea></p>
    <p>Estado: ${estadoOptions} <span class="badge ${s.estado}">${s.estado}</span></p>
    <p>Calificación: ${s.calificacion || "No calificada"}</p>
    <button onclick="guardarCambios(${s.id})">Guardar Cambios</button>
    <button onclick="borrarSolicitud(${s.id})" style="background-color:#e74c3c">Borrar Solicitud</button>
    `;

        div.appendChild(card);
    });

    app.appendChild(div);
}

function guardarCambios(id) {
    const titulo = document.getElementById(`titulo-${id}`).value.trim();
    const descripcion = document.getElementById(`desc-${id}`).value.trim();
    const estado = document.getElementById(`estado-${id}`).value;
    if (titulo && descripcion) {
        const s = solicitudes.find(s => s.id === id);
        s.titulo = titulo; s.descripcion = descripcion; s.estado = estado;
        saveStorage(); alert("Cambios guardados"); renderApp();
    } else alert("Título y descripción no pueden estar vacíos");
}

function borrarSolicitud(id) {
    if (confirm("¿Borrar esta solicitud?")) {
        solicitudes = solicitudes.filter(s => s.id !== id);
        saveStorage(); renderApp();
    }
}

// --- Gráfico con Chart.js ---
function renderGrafico() {
    app.innerHTML = `<h2>Gráfico de Solicitudes</h2><canvas id="chartSolicitudes" height="150"></canvas>`;

    const pendientes = solicitudes.filter(s => s.estado === "pendiente").length;
    const completadas = solicitudes.filter(s => s.estado === "completada").length;

    const ctx = document.getElementById('chartSolicitudes').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Pendientes', 'Completadas'],
            datasets: [{
                label: 'Cantidad de Solicitudes',
                data: [pendientes, completadas],
                backgroundColor: ['#3498db', '#2ecc71']
            }]
        },
        options: { responsive: true, plugins: { legend: { display: false } } }
    });
}

// Inicializar
renderApp();
