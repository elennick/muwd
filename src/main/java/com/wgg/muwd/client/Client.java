package com.wgg.muwd.client;

import lombok.Data;
import lombok.NonNull;

@Data
abstract class Client {

    @NonNull
    private String name;

    @NonNull
    private Long currentRoom;

    public Client() {
        this.name = "NULL";
        this.currentRoom = 1L;
    }

    public Client(String name, Long currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
    }
}
