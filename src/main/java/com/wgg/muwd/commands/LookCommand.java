package com.wgg.muwd.commands;

import com.wgg.muwd.service.CommandRegistry;

import java.util.Arrays;
import java.util.List;

public class LookCommand extends Command {
    @Override
    public String getCommandValue() {
        return "look";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("l", "loo", "view");
    }

    @Override
    public String getResponse(String[] input, CommandRegistry commandRegistry) {
        return "you see stuff";
    }

    @Override
    public String getHelpText() {
        return "Displays a description of your current location";
    }
}
