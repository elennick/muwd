package com.wgg.muwd.command;

import com.wgg.muwd.websocket.Client;
import com.wgg.muwd.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MoveCommand extends Command {

    @Override
    public String getCommandValue() {
        return "move";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("go", "walk");
    }

    @Override
    public Optional<String> getResponse(String[] input, World world, Client client) {
        if (input.length <= 1) {
            return Optional.of("What direction?");
        }
        return Optional.of("moving " + input[1] + "...");
    }

    @Override
    public String getHelpText() {
        return "Moves yourself in a direction (ie: 'move west', 'move up')";
    }
}
