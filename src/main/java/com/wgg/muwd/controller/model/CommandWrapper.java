package com.wgg.muwd.controller.model;

import com.wgg.muwd.websocket.Client;

public class CommandWrapper {
    private String command;
    private Client client;

    public CommandWrapper() {}

    public CommandWrapper(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommandWrapper{");
        sb.append("command='").append(command).append('\'');
        sb.append(", client=").append(client);
        sb.append('}');
        return sb.toString();
    }
}
