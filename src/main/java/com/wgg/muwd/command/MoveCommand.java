package com.wgg.muwd.command;

import com.wgg.muwd.command.service.CommandRegistry;

import java.util.Arrays;
import java.util.List;

public class MoveCommand extends Command {

    @Override
    public String getCommandValue() {
        return "move";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("go","walk");
    }

    @Override
    public String getResponse(String[] input, CommandRegistry commandRegistry) {
        if(input.length <= 1) {
            return "what direction???";
        }
        return "moving " + input[1] + "...";
    }

    @Override
    public String getHelpText() {
        return "Moves yourself in a direction (ie: 'move west', 'move up')";
    }
}
