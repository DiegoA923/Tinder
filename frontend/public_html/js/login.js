const form = document.getElementById("loginForm");
const mensaje = document.getElementById("mensaje");

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const data = {
        nickname: document.getElementById("nickname").value,
        password: document.getElementById("password").value
    };

    // Validación rápida antes de enviar
    if (!data.nickname || !data.password) {
        mensaje.textContent = "Debe llenar todos los campos.";
        mensaje.style.color = "red";
        return;
    }

    try {
        const response = await fetch("http://localhost:8090/usuarios/login", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(data)
        });

        // Si el backend devuelve error 401, 404, 500, etc.
        if (!response.ok) {
            const errorText = await response.text();
            mensaje.textContent = "Error: " + errorText;
            mensaje.style.color = "red";
            return; // NO redirige
        }

        const result = await response.json();

        mensaje.textContent = "Inicio de sesión exitoso ✔";
        mensaje.style.color = "lightgreen";

        // Guardamos información del usuario para futuras pantallas
        localStorage.setItem("usuario", JSON.stringify(result));

        // Redirige a la pantalla principal
        setTimeout(() => {
            window.location.href = "home.html";
        }, 1000);

    } catch (error) {
        mensaje.textContent = "No se pudo conectar con el servidor.";
        mensaje.style.color = "red";
    }
});

function goBack() {
    window.location.href = "index.html";
}
