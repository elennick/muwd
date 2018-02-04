package com.wgg.muwd.command;

import com.wgg.muwd.client.PlayerCharacter;
import com.wgg.muwd.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class InfoCommand extends Command {

    @Override
    public String getCommandValue() {
        return "info";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("stats", "st", "i", "inf", "me", "self");
    }

    @Override
    public Optional<String> getResponse(String[] input, World world, PlayerCharacter client) {
        return Optional.of("Your name: " + client.getName());
    }

    @Override
    public String getHelpText() {
        return "Displays information about you.";
    }

}
