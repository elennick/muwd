package com.wgg.muwd.command;

import com.wgg.muwd.command.service.CommandRegistry;
import com.wgg.muwd.websocket.ClientRegistry;
import com.wgg.muwd.world.World;

import java.util.Arrays;
import java.util.List;

public class BroadcastCommand extends Command {

    @Override
    public String getCommandValue() {
        return "broadcast";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("yell");
    }

    @Override
    public String getResponse(String[] input, World world,
                              CommandRegistry commandRegistry, ClientRegistry clientRegistry) {

        return "This command isn't quite implemented yet!"; //TODO implement this
    }

    @Override
    public String getHelpText() {
        return "Broadcasts a message to everyone in the world.";
    }
}
