const welcomeText = document.getElementById("welcome-text");
const chatList = document.getElementById("chat-list");
const logoutBtn = document.getElementById("logout-btn");
const messageEl = document.getElementById("username");

// Mock data
const recentChats = [
  { id: 1, name: "Spring Boot Team" },
  { id: 2, name: "MongoDB Learners" },
  { id: 3, name: "Frontend Dev Group" },
];

// Render chats
chatList.innerHTML = "";
recentChats.forEach((chat) => {
  const li = document.createElement("li");
  li.textContent = chat.name;
  chatList.appendChild(li);
});

document.addEventListener("DOMContentLoaded", async () => {
  const token = localStorage.getItem("token");

  if (!token) {
    messageEl.textContent = "No token found. Please log in.";
    return;
  }

  try {
    const res = await fetch("http://localhost:8080/User/home", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
    });

    if (!res.ok) {
      const errorData = await res.json().catch(() => ({}));
      throw new Error(errorData.message || `Error: ${res.status}`);
    }

    const data = await res.json();

    messageEl.textContent = data.username;
    welcomeText.textContent = `Welcome, ${data.username}!`;

  } catch (err) {
    console.error("Error fetching home:", err);
    messageEl.textContent = "Error loading username";
  }

  // Example chat log request (FIXED placeholder)
  try {
    const response = await fetch("http://localhost:8080/User/chatLog?limit=3", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
    });

    const chatData = await response.json();
    console.log("Chat logs:", chatData);

  } catch (err) {
    console.error("Chat log error:", err);
  }
});

logoutBtn.addEventListener("click", () => {
  alert("Logged out!");
  localStorage.removeItem("token");
  window.location.href = "index.html";
});


// Modal logic
const modal = document.getElementById("chatModal");
const btn = document.getElementById("createChatBtn");
const span = document.getElementsByClassName("close")[0];
const submitBtn = document.getElementById("submitChat");
const input = document.getElementById("userIdsInput");

// Open modal
btn.onclick = () => {
  modal.style.display = "block";
};

// Close modal
span.onclick = () => {
  modal.style.display = "none";
};

window.onclick = (event) => {
  if (event.target === modal) modal.style.display = "none";
};

// Submit chat
submitBtn.onclick = async () => {
  const userIds = input.value;

  if (!userIds) {
    alert("Please enter at least one user ID.");
    return;
  }

  try {
    const response = await fetch("http://localhost:8080/createChat", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(userIds.split(",").map((id) => id.trim())),
    });

    if (!response.ok) {
      throw new Error("Failed to create chat");
    }

    const chat = await response.json();
    alert(`Chat created! Chat ID: ${chat.id}`);

    modal.style.display = "none";
    input.value = "";

  } catch (error) {
    console.error("Error:", error);
    alert("Error creating chat.");
  }
};