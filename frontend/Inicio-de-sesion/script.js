const container = document.querySelector(".container");
const btnSignIn = document.getElementById("btn-sign-in");
const btnSignUp = document.getElementById("btn-sign-up");

btnSignIn.addEventListener("click", ()=>{
    container.classList.remove("toggle");
});

btnSignUp.addEventListener("click",()=> {
    container.classList.add("toggle");
});

const form = document.getElementById("register-form");

form.addEventListener("submit", (e) => {
    e.preventDefault();

    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;

    if(password !== confirmPassword){
        alert("Las contraseñas no coinciden");
    } else {
        alert("Registro exitoso");
        // Aquí podrías enviar los datos al servidor
        form.reset();
    }
});
