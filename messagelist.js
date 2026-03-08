document.addEventListener("DOMContentLoaded", () => {
    const chatHeader = document.getElementById("chatHeader");
    const chatMessages = document.getElementById("chatMessages");
    const messageInput = document.getElementById("messageInput");
    const sendBtn = document.getElementById("sendBtn");

    // For demo, set chat name
    chatHeader.textContent = "General Chat";

    // Handle sending messages
    sendBtn.onclick = sendMessage;
    messageInput.addEventListener("keydown", (e) => {
        if (e.key === "Enter") sendMessage();
    });

    function sendMessage() {
        const text = messageInput.value.trim();
        if (!text) return;

        const msgDiv = document.createElement("div");
        msgDiv.className = "message sent";
        msgDiv.textContent = text;
        chatMessages.appendChild(msgDiv);
        chatMessages.scrollTop = chatMessages.scrollHeight;

        // For demo: auto-reply
        setTimeout(() => {
            const replyDiv = document.createElement("div");
            replyDiv.className = "message received";
            replyDiv.textContent = "Auto-reply: " + text;
            chatMessages.appendChild(replyDiv);
            chatMessages.scrollTop = chatMessages.scrollHeight;
        }, 800);

        messageInput.value = "";
    }
});
