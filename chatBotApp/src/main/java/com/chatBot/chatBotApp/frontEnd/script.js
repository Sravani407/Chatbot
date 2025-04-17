// Get form and response elements
const form = document.getElementById('chatForm');
const responseDiv = document.getElementById('response');

// Add event listener for form submission
form.addEventListener('submit', function(event) {
    event.preventDefault();  // Prevent page reload

    // Get message input value
    const message = document.getElementById('message').value;

    // Send POST request to backend (Spring Boot API)
    fetch('http://localhost:8080/api/chat', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'  // Indicating JSON data
        },
        body: JSON.stringify({ message: message })  // Send the message in JSON format
    })
    .then(response => response.json())  // Parse JSON response
    .then(data => {
        // Display the response from the backend (Chatbot response)
        responseDiv.innerHTML = `Bot: ${data.reply}`;
    })
    .catch(error => {
        // Handle any errors
        console.error('Error:', error);
        responseDiv.innerHTML = 'Error: Could not communicate with the bot.';
    });
});
