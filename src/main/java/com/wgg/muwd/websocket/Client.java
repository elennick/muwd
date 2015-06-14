package com.wgg.muwd.websocket;

public class Client {

    private String webSocketSessionId;
    private Long currentRoom;

    public Client(String webSocketSessionId, Long currentRoom) {
        this.webSocketSessionId = webSocketSessionId;
        this.currentRoom = currentRoom;
    }

    public String getWebSocketSessionId() {
        return webSocketSessionId;
    }

    public void setWebSocketSessionId(String webSocketSessionId) {
        this.webSocketSessionId = webSocketSessionId;
    }

    public Long getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Long currentRoom) {
        this.currentRoom = currentRoom;
    }
}
