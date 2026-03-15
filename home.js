document.addEventListener("DOMContentLoaded", async () => {
  const welcomeText = document.getElementById("welcome-text");
  const chatList = document.getElementById("chat-list");
  const logoutBtn = document.getElementById("logout-btn");
  const viewAllBtn = document.getElementById("view-all-chats");
  const searchBtn = document.getElementById("search-btn");
  const userSearch = document.getElementById("user-search");

  const modal = document.getElementById("chatModal");
  const btn = document.getElementById("createChatBtn");
  const span = document.getElementsByClassName("close")[0];
  const submitBtn = document.getElementById("submitChat");
  const input = document.getElementById("userIdsInput");

  // Mock user & recent chats
// removed this   const user = { username: "Alice" };











  // Display welcome
// removed this  welcomeText.textContent = `Hello, ${user.username}!`;
const token = localStorage.getItem("token");

console.log(token);

let username = "";
const recentChats = [];

// Fetch username
async function fetchUsername(token) {
    const res = await fetch("http://localhost:8080/User/home", {
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        }
    });
    const data = await res.json();
    username = data.username;

    welcomeText.textContent = `Hello, ${username}!`;
}

// Fetch recent chats
async function fetchRecentChats(token) {
    const res = await fetch("http://localhost:8080/User/recents?limit=5", {
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        }
    });
    const data = await res.json();
    recentChats.length = 0;          // clear old data
    recentChats.push(...data);        // save new data
}


await fetchUsername(token);

// Get recent chats
await fetchRecentChats(token);

console.log(recentChats);

 // should be JWT
 //






  // Display recent chats (1–5)
  chatList.innerHTML = "";
  recentChats.slice(0,5).forEach(chat => {
    const li = document.createElement("li");
    li.textContent = chat.name;
    li.onclick = () => alert(`Go to chat ${chat.name}`);
    chatList.appendChild(li);
  });
















  // Logout
  logoutBtn.onclick = () => {
    alert("Logged out!");
    window.location.href = "index.html";
  };

  // View all chats
  viewAllBtn.onclick = () => {
    window.location.href = "all-chats.html";
  };

  // Search users
  searchBtn.onclick = () => {
    const query = userSearch.value.trim();
    if (!query) return alert("Enter a username to search");
    alert(`Searching for user: ${query}`);
  };

  // Modal logic
  btn.onclick = () => modal.style.display = "block";
  span.onclick = () => modal.style.display = "none";
  window.onclick = (event) => {
    if (event.target == modal) modal.style.display = "none";
  };

  submitBtn.onclick = () => {
    const userIds = input.value.split(",").map(id => id.trim()).filter(Boolean);
    if (userIds.length === 0) return alert("Enter at least one user ID");
    alert(`Creating chat with: ${userIds.join(", ")}`);
    modal.style.display = "none";
    input.value = "";
  };



  
   






});