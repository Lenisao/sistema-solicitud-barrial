const container = document.querySelector(".container");
const btnSignIn = document.getElementById("btn-sign-in");
const btnSignUp = document.getElementById("btn-sign-up");

// Cambiar entre login y registro
btnSignIn.addEventListener("click", () => {
    container.classList.remove("toggle");
});

btnSignUp.addEventListener("click", () => {
    container.classList.add("toggle");
});

document.addEventListener("DOMContentLoaded", () => {
    // ------------------------- LOGIN -------------------------
    const loginForm = document.getElementById("loginForm");
    const mensaje = document.getElementById("mensaje");

    loginForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        try {
            const response = await fetch("http://localhost:8080/api/seguridad/existeUsuario", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    loginUsuario: email,
                    claveUsuario: password
                })
            });

            const data = await response.json();
            console.log("Respuesta login:", data);

            if (data.codigoUsuario && data.codigoUsuario > 0) {
                mensaje.textContent = "Inicio de sesión correcto";
                mensaje.style.color = "green";

                setTimeout(() => {
                    window.location.href = "../dashboard/panel-de-control.html";
                }, 1000);
            } else {
                mensaje.textContent = "Usuario o contraseña incorrectos";
                mensaje.style.color = "red";
            }

        } catch (error) {
            console.error("Error al consumir API:", error);
            mensaje.textContent = "Error al conectar con el servidor";
            mensaje.style.color = "red";
        }
    });

    // ------------------------- REGISTRO -------------------------
    const signupForm = document.getElementById("signupForm");
    const signupMensaje = document.getElementById("signupMensaje");

    signupForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const nombre = document.getElementById("nombre").value;
        const identificacion = document.getElementById("identificacion").value;
        const correo = document.getElementById("correo").value;
        const clave = document.getElementById("clave").value;

        try {
            const response = await fetch("http://localhost:8080/api/seguridad/mantenimientoUsuario", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    codigoUsuario: 0,
                    loginUsuario: correo,
                    nombreUsuario: nombre,
                    numeroIdentificacion: identificacion,
                    claveUsuario: clave,
                    estadoUsuario: true,
                    descripcionEstadoUsuario: ""
                })
            });

            const data = await response.json();
            console.log("Respuesta registro:", data);

            if (data.codigoUsuario && data.codigoUsuario > 0) {
                signupMensaje.textContent = "Registro exitoso";
                signupMensaje.style.color = "green";

                setTimeout(() => {
                    window.location.href = "../dashboard/panel-de-control.html";
                }, 1000);
            } else {
                signupMensaje.textContent = "Error al registrar usuario";
                signupMensaje.style.color = "red";
            }

        } catch (error) {
            console.error("Error al consumir API:", error);
            signupMensaje.textContent = "Error al conectar con el servidor";
            signupMensaje.style.color = "red";
        }
    });
});
