package com.wgg.muwd.command;

import com.wgg.muwd.websocket.Client;
import com.wgg.muwd.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public Optional<String> getResponse(String[] input, World world, Client client) {
        return Optional.of("you see stuff");
    }

    @Override
    public String getHelpText() {
        return "Displays a description of your current location";
    }
}
