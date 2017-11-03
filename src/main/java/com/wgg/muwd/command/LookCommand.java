package com.wgg.muwd.command;

import com.wgg.muwd.command.service.CommandRegistry;
import com.wgg.muwd.websocket.ClientRegistry;
import com.wgg.muwd.world.World;

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
    public String getResponse(String[] input, World world,
                              CommandRegistry commandRegistry, ClientRegistry clientRegistry) {

        return "you see stuff";
    }

    @Override
    public String getHelpText() {
        return "Displays a description of your current location";
    }
}
