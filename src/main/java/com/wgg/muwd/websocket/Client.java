package com.wgg.muwd.websocket;

public class Client {

    private String webSocketSessionId;
    private String name;
    private Long currentRoom;

    public Client(String webSocketSessionId, String name, Long currentRoom) {
        this.webSocketSessionId = webSocketSessionId;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " (" + webSocketSessionId + ")";
    }
}
