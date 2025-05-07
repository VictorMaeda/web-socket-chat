var stompClient = null;

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/sala1/messages', function (message) {
            try {
                const parsedMessage = JSON.parse(message.body);
                showMessage(parsedMessage);
            } catch (e) {
                console.error("Erro ao parsear message.body:", e);
            }
        });
    });
}
function showMessage(message) {
    console.log(message)
    var messageElement = document.createElement("div");
    messageElement.className = "message";
    messageElement.innerHTML = `<strong>${message.username}:</strong> ${message.message} <span class="timestamp">${formatarHorario(message.horario)}</span>`;
    document.getElementById("messages").appendChild(messageElement);
}

function formatarHorario(horario) {
    return horario.split(":").slice(0, 2).join(":");
}

function handleLogin() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    clearFields()
}

function handleRegister() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    clearFields()
}
function clearFields() {
    document.getElementById('username').value = '';
    document.getElementById('password').value = '';
}




function sendMessage() {
    var messageInput = document.getElementById("messageInput").value;
    if (messageInput) {
        postMessage(messageInput)
        document.getElementById("messageInput").value = '';
    }
}
function postMessage(message) {
    fetch('/message', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: "test user",
            message: message
        })
    })
        .then(response => {
            if (response.status === 400) {
                return response.text().then(errorMessage => {
                    throw new Error(errorMessage);
                });
            } else if (!response.ok) {
                throw new Error("Erro na requisição");
            }
            return response.text();
        })
        .then(data => {
            console.log("Resposta do servidor:", data);
        })
        .catch(error => {
            console.error("Erro:", error.message);
        });
}


window.onload = function() {
    connect();
};