package com.wgg.muwd.client;

import lombok.Data;
import lombok.NonNull;

@Data
abstract public class Client {

    @NonNull
    private String name;

    @NonNull
    private Long currentRoom;

    public Client() {}

    public Client(String name, Long currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
    }
}
