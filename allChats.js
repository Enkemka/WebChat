const chatList = document.getElementById("chatList");
const token = localStorage.getItem("token");

card.onclick = () => {
    window.location.href = `chat.html?chatId=${chat.chatId}`;
};
async function loadChats(){

    const res = await fetch("http://localhost:8080/User/all-chats",{
        headers:{
            "Authorization":"Bearer " + token,
            "Content-Type":"application/json"
        }
    });

    const chats = await res.json();

    chatList.innerHTML = "";

    chats.forEach(chat => {

        const card = document.createElement("div");
        card.classList.add("chat-card");

        card.innerHTML = `
            <div class="chat-name">${chat.chatName ?? "Chat"}</div>
            <div class="chat-message">${chat.recentChatSender}: ${chat.recentChatMessage}</div>
            <div class="chat-meta">
                <span>${chat.time}</span>
            </div>
        `;

        card.onclick = () => {
            window.location.href = `chat.html?chatId=${chat.chatId}`;
        };

        chatList.appendChild(card);
    });
}

loadChats();