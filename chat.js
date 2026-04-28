const token = localStorage.getItem("token")

const urlParams = new URLSearchParams(window.location.search)
const chatId = urlParams.get("chatId")

const messagesDiv = document.getElementById("messages")

async function loadChat(){

    const res = await fetch(`http://localhost:8080/chat/${chatId}/view`,{
        headers:{
            "Authorization":"Bearer "+token
        }
    })

    const messages = await res.json()

    messagesDiv.innerHTML=""

    messages.forEach(m => {

        const div = document.createElement("div")
        div.classList.add("message")

        div.innerHTML = `
        <div class="sender">${m.senderName}</div>
        <div>${m.message}</div>
        <div class="time">${m.creationDate}</div>
        `

        messagesDiv.appendChild(div)

    })

}

async function sendMessage(){

    const text = document.getElementById("messageInput").value

    const message = {
        message:text
    }

    await fetch(`http://localhost:8080/chat/${chatId}/add`,{
        method:"PATCH",
        headers:{
            "Authorization":"Bearer "+token,
            "Content-Type":"application/json"
        },
        body:JSON.stringify(message)
    })

    loadChat()
}

async function deleteChat(){

    await fetch(`http://localhost:8080/chat/deleteChat/${chatId}`,{
        method:"DELETE",
        headers:{
            "Authorization":"Bearer "+token
        }
    })

    window.location.href="allChats.html"
}

async function removeMessage(){

    const id=document.getElementById("removeMessageId").value

    await fetch(`http://localhost:8080/chat/${chatId}/remove?messageId=${id}`,{
        method:"PATCH",
        headers:{
            "Authorization":"Bearer "+token
        }
    })

    loadChat()
}

async function addUser(){

    const userId=document.getElementById("addUserId").value

    await fetch(`http://localhost:8080/chat/${chatId}/add/${userId}`,{
        method:"PATCH",
        headers:{
            "Authorization":"Bearer "+token
        }
    })
}

async function deleteUser(){

    const userId=document.getElementById("deleteUserId").value

    await fetch(`http://localhost:8080/chat/${chatId}/delete/${userId}`,{
        method:"PATCH",
        headers:{
            "Authorization":"Bearer "+token
        }
    })
}

loadChat()