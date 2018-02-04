package com.wgg.muwd.client;

import lombok.Data;
import lombok.NonNull;

@Data
public class PlayerCharacter extends Client {

    public PlayerCharacter(String webSocketSessionId, String name, Long currentRoom) {
        super(name, currentRoom);
        this.webSocketSessionId = webSocketSessionId;
    }

    @NonNull
    private String webSocketSessionId;

}
