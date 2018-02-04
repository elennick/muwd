package com.wgg.muwd.controller.model;

import com.wgg.muwd.client.PlayerCharacter;
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

    private PlayerCharacter client;

    public void setCommand(String command) {
        this.command = command;
    }

}
