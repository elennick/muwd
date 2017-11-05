package com.wgg.muwd.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Client {

    @NonNull
    private String webSocketSessionId;

    @NonNull
    private String name;

    @NonNull
    private Long currentRoom;

}
