package com.wgg.muwd.commands;

import com.wgg.muwd.service.CommandRegistry;

public class LookCommand extends Command {
    @Override
    public String getCommandValue() {
        return "look";
    }

    @Override
    public String getResponse(String input, CommandRegistry commandRegistry) {
        return "you see stuff";
    }
}
