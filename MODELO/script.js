// ---- LOGIN ----
const loginForm = document.getElementById("loginForm");
if (loginForm) {
    loginForm.addEventListener("submit", (e) => {
        e.preventDefault();

        const username = document.getElementById("username").value.trim();
        const password = document.getElementById("password").value.trim();
        const message = document.getElementById("message");

        const users = JSON.parse(localStorage.getItem("users")) || [];

        const found = users.find(u => u.username === username && u.password === password);

        if (found) {
            localStorage.setItem("loggedUser", username);
            message.style.color = "green";
            message.textContent = "Inicio de sesión exitoso ✅";
            setTimeout(() => window.location.href = "dashboard.html", 1000);
        } else {
            message.style.color = "red";
            message.textContent = "Usuario o contraseña incorrectos.";
        }
    });
}

// ---- REGISTRO ----
const registerForm = document.getElementById("registerForm");
if (registerForm) {
    registerForm.addEventListener("submit", (e) => {
        e.preventDefault();

        const username = document.getElementById("newUsername").value.trim();
        const password = document.getElementById("newPassword").value.trim();
        const message = document.getElementById("message");

        if (username === "" || password === "") {
            message.style.color = "red";
            message.textContent = "Por favor completa todos los campos.";
            return;
        }

        let users = JSON.parse(localStorage.getItem("users")) || [];

        if (users.some(u => u.username === username)) {
            message.style.color = "red";
            message.textContent = "El usuario ya existe.";
            return;
        }

        users.push({ username, password });
        localStorage.setItem("users", JSON.stringify(users));

        message.style.color = "green";
        message.textContent = "Registro exitoso ✅";
        setTimeout(() => window.location.href = "index.html", 1000);
    });
}
