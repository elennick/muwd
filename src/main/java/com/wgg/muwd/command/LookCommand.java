package com.wgg.muwd.command;

import com.wgg.muwd.websocket.Client;
import com.wgg.muwd.world.Room;
import com.wgg.muwd.world.World;
import com.wgg.muwd.world.service.WorldManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LookCommand extends Command {

    @Autowired
    private WorldManager worldManager;

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
        Room room = worldManager.getCurrentRoom(client);
        return Optional.of(room.getTerminalFormattedText());
    }

    @Override
    public String getHelpText() {
        return "Displays a description of your current location";
    }
}
