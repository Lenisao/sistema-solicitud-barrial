const container = document.querySelector(".container");
const btnSignIn = document.getElementById("btn-sign-in");
const btnSignUp = document.getElementById("btn-sign-up");

btnSignIn.addEventListener("click", () => {
    container.classList.remove("toggle");
});

btnSignUp.addEventListener("click", () => {
    container.classList.add("toggle");
});

const form = document.getElementById("register-form");

form.addEventListener("submit", (e) => {
    e.preventDefault();

    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;

    if (password !== confirmPassword) {
        alert("Las contraseñas no coinciden");
    } else {
        alert("Registro exitoso");
        // Aquí podrías enviar los datos al servidor
        form.reset();
    }
});


//Consumir el API para logins
document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.querySelector(".sign-in");

    loginForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        // tomar valores del form
        const correo = loginForm.querySelector('input[type="email"]').value;
        const clave = loginForm.querySelector('input[type="password"]').value;

        try {
            const response = await fetch("http://localhost:8080/api/seguridad/existeUsuario", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    correo: correo,   // debe coincidir con UsuarioDto
                    clave: clave
                })
            });

            if (response.ok) {
                const data = await response.json();
                console.log("Login exitoso:", data);

                // ejemplo: guardar usuario en localStorage
                localStorage.setItem("usuario", JSON.stringify(data));

                // redirigir a otra página
                window.location.href = "dashboard.html";
            } else if (response.status === 401) {
                alert("Credenciales incorrectas");
            } else {
                alert("Error al iniciar sesión");
            }
        } catch (error) {
            console.error("Error de red:", error);
            alert("No se pudo conectar con el servidor.");
        }
    });
});

