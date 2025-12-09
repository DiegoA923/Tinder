const form = document.getElementById("registroForm");
const mensaje = document.getElementById("mensaje");

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const data = {
        nickname: document.getElementById("nickname").value,
        nombre: document.getElementById("nombre").value,
        edad: parseInt(document.getElementById("edad").value),
        ciudad: document.getElementById("ciudad").value,
        correo: document.getElementById("correo").value,
        password: document.getElementById("password").value,
        descripcion: document.getElementById("descripcion").value,
        genero: document.getElementById("genero").value,
        fotoPerfil: document.getElementById("fotoPerfil").value
    };

    // ---------------------------
    // VALIDACIONES DEL FRONTEND
    // ---------------------------
    let errores = [];

    if (data.password.length < 8) {
        errores.push("La contraseña debe tener mínimo 8 caracteres.");
    }
    if (!/[A-Z]/.test(data.password)) {
        errores.push("Debe tener al menos una letra mayúscula.");
    }
    if (!/[a-z]/.test(data.password)) {
        errores.push("Debe tener al menos una letra minúscula.");
    }
    if (!/[0-9]/.test(data.password)) {
        errores.push("Debe tener al menos un número.");
    }
    if (!/[^A-Za-z0-9]/.test(data.password)) {
        errores.push("Debe tener al menos un carácter especial.");
    }

    if (errores.length > 0) {
        mensaje.innerHTML = errores.join("<br>");
        mensaje.style.color = "red";
        return; // NO ENVÍA NADA AL BACKEND
    }

    try {
        const response = await fetch("http://localhost:8090/usuarios/registrar", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(data)
        });

        // ---------------------------
        // VALIDAR RESPUESTA DEL BACKEND
        // ---------------------------
        if (!response.ok) {
            const errorText = await response.text();
            mensaje.innerHTML = "Error: " + errorText;
            mensaje.style.color = "red";
            return;
        }

        // Si todo salió bien:
        const result = await response.json();
        mensaje.textContent = "Usuario registrado correctamente 🎉";
        mensaje.style.color = "lightgreen";

        setTimeout(() => {
            window.location.href = "login.html";
        }, 1500);

    } catch (error) {
        mensaje.textContent = "Error al conectar con el servidor.";
        mensaje.style.color = "red";
    }
});

function goBack() {
    window.location.href = "index.html";
}
