async function register() {

    const username = document.getElementById("regUsername").value;
    const password = document.getElementById("regPassword").value;

    const res = await fetch('/api/admin/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    });

    const result = await res.json();

    if (result.status === "CREATED") {
        document.getElementById("regMsg").innerText = "Admin Created! You can login now.";
    } else {
        document.getElementById("regMsg").innerText = "Username already exists.";
    }
}

