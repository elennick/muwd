package com.wgg.muwd.client;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class PlayerCharacter extends Client {

    @NonNull
    private String webSocketSessionId;

    public PlayerCharacter(String webSocketSessionId, String name, Long currentRoom) {
        super(name, currentRoom);
        this.webSocketSessionId = webSocketSessionId;
    }

}
