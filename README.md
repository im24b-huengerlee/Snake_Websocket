# Spring Boot WebSocket (STOMP over SockJS) — Minimal Example

This is a tiny full-stack demo:
- **Backend**: Spring Boot WebSocket + STOMP + SockJS endpoint at `/ws`
- **Broker**: simple broker enabled for `/topic`
- **Publisher**: broadcasts `"Update 1"`, `"Update 2"`, ... every second to `/topic/updates`
- **Frontend**: plain `index.html` subscribes and appends messages

## Run

From the project folder:

```bash
./mvnw spring-boot:run
```

On Windows (PowerShell):

```powershell
.\mvnw.cmd spring-boot:run
```

Open:
- `http://localhost:8080/`

Click **Connect** and you should see:

```
Update 1
Update 2
Update 3
...
```

## Key files

- `src/main/java/com/example/websockettest/WebSocketConfig.java`
- `src/main/java/com/example/websockettest/UpdatesController.java`
- `src/main/resources/static/index.html`

