package com.wgg.muwd.controller.model;

import com.wgg.muwd.websocket.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandWrapper {

    @NonNull
    private String command;

    private Client client;

    public void setCommand(String command) {
        this.command = command;
    }

}
