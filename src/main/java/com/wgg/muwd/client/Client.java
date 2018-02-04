package com.wgg.muwd.client;

import lombok.Data;
import lombok.NonNull;

@Data
abstract public class Client {

    @NonNull
    private String name;

    @NonNull
    private Long currentRoom;

    @NonNull
    private Long loggedInAt;

    public Client() {
        this.loggedInAt = System.currentTimeMillis();
    }

    public Client(String name, Long currentRoom) {
        this();
        this.name = name;
        this.currentRoom = currentRoom;
    }
}
