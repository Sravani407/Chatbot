# Rasa Chatbot — Spring Boot

A full-stack AI-powered chatbot. The Spring Boot backend
acts as middleware between the HTML/JS chat frontend and
the Rasa NLP server, forwarding user messages and returning
AI-generated responses in real time.

---

## 🏗️ Architecture

```
[ Browser — index.html + script.js ]
        ↓  POST /api/chat  { "message": "..." }
[ Spring Boot — chatController.java ]
        ↓  HTTP POST to localhost:5005
[ Rasa NLP Server — /webhooks/rest/webhook ]
        ↓  returns [ { "text": "bot reply" } ]
[ rasaService.java — extracts reply text ]
        ↓
[ ChatResponse returned to frontend ]
```

---

## 📁 Project Structure

```
src/main/java/com/chatBot/chatBotApp/
├── controller/
│   └── chatController.java     → POST /api/chat endpoint
│                                 Accepts ChatMessage, returns ChatResponse
├── model/
│   └── userMessage.java        → User message model
├── service/
│   └── rasaService.java        → Calls Rasa webhook via RestTemplate
│                                 Sends sender + message, extracts reply
├── frontEnd/
│   ├── index.html              → Chat UI
│   └── script.js               → Sends messages, displays bot replies
└── ChatBotAppApplication.java  → Spring Boot entry point
```

---

## 🔌 API

### Send a message
```
POST /api/chat
Content-Type: application/json

{ "message": "Hello" }
```

### Response
```json
{ "reply": "Hi! How can I help you?" }
```

Internally, `rasaService` forwards the message to Rasa:
```
POST http://localhost:5005/webhooks/rest/webhook
{ "sender": "user1", "message": "Hello" }
```

---

## 🛠️ Tech Stack

- **Java / Spring Boot** — REST API and middleware layer
- **RestTemplate** — HTTP client to communicate with Rasa
- **Rasa** — Open source NLP and conversational AI server
- **HTML & JavaScript** — Frontend chat interface
- **Maven** — Build and dependency management

---

## ▶️ How to Run

**Prerequisites:** Java 8+, Maven, Rasa installed

1. Start the Rasa server:
```bash
rasa run --enable-api --cors "*"
```

2. Run the Spring Boot app:
```bash
cd chatBotApp
./mvnw spring-boot:run
```

3. Open in browser:
```
http://localhost:8080
```

---

## 💡 Key Concepts Demonstrated

- REST API design with Spring Boot (@RestController, @PostMapping)
- HTTP client integration using RestTemplate
- Service layer communication with external AI server (Rasa)
- JSON request/response handling with inner model classes
- Full-stack development — Spring Boot backend + HTML/JS frontend
- Layered architecture — Controller → Service → NLP integration
- Graceful fallback — returns default reply if Rasa has no response
