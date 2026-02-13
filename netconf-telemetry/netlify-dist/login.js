async function login() {

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const res = await fetch('/api/admin/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    });

    const result = await res.json();

    if (result.status === "SUCCESS") {

        localStorage.setItem("adminLoggedIn", "true");
        window.location.href = "/";

    } else {

        document.getElementById("errorMsg").innerText = "Invalid Credentials";
    }
}

