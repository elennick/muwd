package com.wgg.muwd.command;

import com.wgg.muwd.client.PlayerCharacter;
import com.wgg.muwd.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
        final long loggedInForMs = System.currentTimeMillis() - client.getLoggedInAt();
        final long loggedInForSeconds = TimeUnit.MILLISECONDS.toSeconds(loggedInForMs);

        String stringBuilder = "<span style=\"color: red;\">--==** " + client.getName() + " **==--</span><br/>" +
                "<span style=\"color: white;\">Logged in for:</span> " + loggedInForSeconds + " seconds<br/>" +
                "<span style=\"color: white;\">Inventory:</span> EMPTY<br/>";

        return Optional.of(stringBuilder);
    }

    @Override
    public String getHelpText() {
        return "Displays information about you.";
    }

}
