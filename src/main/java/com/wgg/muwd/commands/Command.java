package com.wgg.muwd.commands;

import com.wgg.muwd.service.CommandRegistry;

abstract public class Command {

    abstract public String getCommandValue();

    abstract public String getResponse(String[] input, CommandRegistry commandRegistry);

    @Override
    public String toString() {
        return getCommandValue();
    }
}
