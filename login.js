const form = document.getElementById("auth-form");
const formTitle = document.getElementById("form-title");
const toggleLink = document.getElementById("toggle-link");
const toggleText = document.getElementById("toggle-text");
const nameGroup = document.getElementById("name-group");
const submitBtn = document.getElementById("submit-btn");
const message = document.getElementById("message");

const email = document.getElementById("email-group");

let isLogin = true;

toggleLink.addEventListener("click", (e) => {
  e.preventDefault();
  isLogin = !isLogin;
  if (isLogin) {
    formTitle.textContent = "Login";

    email.style.display = "none";
    emailInput.required = false;

    submitBtn.textContent = "Login";
    toggleText.innerHTML = `Don’t have an account? <a href="#" id="toggle-link">Register</a>`;
  } else {
    formTitle.textContent = "Register";

    email.style.display = "block";




    submitBtn.textContent = "Register";
    toggleText.innerHTML = `Already have an account? <a href="#" id="toggle-link">Login</a>`;
  }
  document.getElementById("toggle-link").addEventListener("click", (e) => toggleLink.click(e));
});

form.addEventListener("submit", async (e) => {
  e.preventDefault();
  message.textContent = "";

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  const username = document.getElementById("username").value;

const baseURL = "http://localhost:8080";
  const url = isLogin ? `${baseURL}/User/login` : `${baseURL}/User/register`;

  const payload = isLogin ?{ username, password }:{ email,username, password };

  try {
    const res = await fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    });

    const data = await res.json();

    if (!res.ok) throw new Error(data.message || "Something went wrong");

    message.style.color = "green";

    message.textContent = isLogin ? "Login successful!" : "Registration successful!";

    localStorage.setItem("token", data.token);
    console.log("Login response:", data);

    window.location.href = "home.html";

  } catch (err) {
    message.style.color = "red";
    message.textContent = err.message;
  }
});
