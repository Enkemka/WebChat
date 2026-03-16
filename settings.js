// Tab switching
const tabs = document.querySelectorAll(".sidebar li");
const tabContents = document.querySelectorAll(".tab-content");

tabs.forEach(tab => {
    tab.addEventListener("click", () => {
        // Remove active from all tabs
        tabs.forEach(t => t.classList.remove("active"));
        tab.classList.add("active");

        // Show corresponding content
        tabContents.forEach(tc => tc.classList.remove("active"));
        const content = document.getElementById(tab.dataset.tab);
        content.classList.add("active");
    });
});

// Account form submission
document.getElementById("accountForm").addEventListener("submit", function(e) {
    e.preventDefault();
    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    console.log("Account updated:", { username, email });
    alert("Account settings saved!");
});

// Security form submission
document.getElementById("securityForm").addEventListener("submit", function(e) {
    e.preventDefault();
    const current = document.getElementById("currentPassword").value;
    const newPass = document.getElementById("newPassword").value;
    const confirm = document.getElementById("confirmPassword").value;

    if(newPass !== confirm){
        alert("New passwords do not match!");
        return;
    }
    console.log("Password updated:", { current, newPass });
    alert("Password updated successfully!");
});

// Notifications form submission
document.getElementById("notificationsForm").addEventListener("submit", function(e){
    e.preventDefault();
    const formData = new FormData(e.target);
    const prefs = Object.fromEntries(formData.entries());
    console.log("Notification preferences:", prefs);
    alert("Notification settings saved!");
});
