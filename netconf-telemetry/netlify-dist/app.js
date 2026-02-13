/* ===========================
   AUTH PROTECTION
=========================== */

if (localStorage.getItem("adminLoggedIn") !== "true") {
    window.location.href = "/login.html";
}

/* ===========================
   VARIABLES
=========================== */

let systemRunning = false;
let intervalId = null;
let refreshRate = 2000;

/* ===========================
   START / STOP SYSTEM
=========================== */

async function toggleSystem() {

    const btn = document.getElementById("systemBtn");
    const badge = document.getElementById("connectionBadge");

    if (!systemRunning) {

        await fetch('/api/netconf/connect', { method: 'POST' });

        systemRunning = true;

        btn.innerText = "STOP SYSTEM";
        btn.classList.remove("start-btn");
        btn.classList.add("stop-btn");

        badge.innerText = "ONLINE";
        badge.classList.add("online");

        intervalId = setInterval(fetchData, refreshRate);
        fetchData();

    } else {

        systemRunning = false;
        clearInterval(intervalId);

        btn.innerText = "START SYSTEM";
        btn.classList.remove("stop-btn");
        btn.classList.add("start-btn");

        badge.innerText = "OFFLINE";
        badge.classList.remove("online");

        document.getElementById("dataTableBody").innerHTML = "";
        document.getElementById("alertsPanel").innerHTML =
            `<div class="no-alert">No active threats detected.</div>`;
        document.getElementById("alertCount").innerText = "0";
    }
}

/* ===========================
   CHANGE REFRESH RATE
=========================== */

function changeRefreshRate() {

    const select = document.getElementById("refreshSelect");
    refreshRate = parseInt(select.value);

    if (systemRunning) {
        clearInterval(intervalId);
        intervalId = setInterval(fetchData, refreshRate);
    }
}

/* ===========================
   FETCH DATA
=========================== */

async function fetchData() {

    if (!systemRunning) return;

    const res = await fetch('/api/netconf/dashboard-data');
    const dataList = await res.json();

    updateTable(dataList);
    loadAlerts();
}

/* ===========================
   UPDATE TABLE
=========================== */

function updateTable(dataList) {

    const tableBody = document.getElementById("dataTableBody");
    tableBody.innerHTML = "";

    dataList.forEach(device => {

        const cpuColor = device.cpuUsage > 80 ? "#ef4444"
                        : device.cpuUsage > 60 ? "#facc15"
                        : "#22c55e";

        const memColor = device.memoryUsage > 80 ? "#ef4444"
                        : device.memoryUsage > 60 ? "#facc15"
                        : "#22c55e";

        const row = `
            <tr>
                <td>${device.deviceName}</td>
                <td>${device.timestamp}</td>
                <td>
                    ${device.cpuUsage}%
                    <div class="progress">
                        <div class="progress-fill"
                             style="width:${device.cpuUsage}%; background:${cpuColor}">
                        </div>
                    </div>
                </td>
                <td>
                    ${device.memoryUsage}%
                    <div class="progress">
                        <div class="progress-fill"
                             style="width:${device.memoryUsage}%; background:${memColor}">
                        </div>
                    </div>
                </td>
            </tr>
        `;
        tableBody.insertAdjacentHTML("beforeend", row);
    });
}

/* ===========================
   LOAD ALERTS
=========================== */

async function loadAlerts() {

    const res = await fetch('/api/alerts');
    const alerts = await res.json();

    const alertPanel = document.getElementById("alertsPanel");
    const alertCount = document.getElementById("alertCount");

    alertPanel.innerHTML = "";
    alertCount.innerText = alerts.length;

    if (alerts.length === 0) {
        alertPanel.innerHTML =
            `<div class="no-alert">No active threats detected.</div>`;
        return;
    }

    alerts.slice(-8).reverse().forEach(alert => {

        const alertHtml = `
            <div class="alert">
                <div><strong>${alert.message}</strong></div>
                <div>${alert.deviceName}</div>
                <div>${alert.timestamp}</div>
            </div>
        `;

        alertPanel.insertAdjacentHTML("beforeend", alertHtml);
    });
}

/* ===========================
   LOGOUT
=========================== */

function logout() {
    localStorage.removeItem("adminLoggedIn");
    window.location.href = "/login.html";
}

