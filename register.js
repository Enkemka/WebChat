const API_URL = "http://localhost:8080/User/register";

async function register(){

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const response = await fetch(API_URL, {
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    });

    const data = await response.json();

    if(response.ok){
        document.getElementById("message").innerText = "Registration successful";
    }else{
        document.getElementById("message").innerText = "Registration failed";
    }

}