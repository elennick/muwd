package com.wgg.muwd.command;

import com.wgg.muwd.client.ClientRegistry;
import com.wgg.muwd.client.PlayerCharacter;
import com.wgg.muwd.world.Room;
import com.wgg.muwd.world.World;
import com.wgg.muwd.world.service.WorldManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LookCommand extends Command {

    @Autowired
    private WorldManager worldManager;

    @Autowired
    private ClientRegistry clientRegistry;

    @Override
    public String getCommandValue() {
        return "look";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("l", "loo", "view");
    }

    @Override
    public Optional<String> getResponse(String[] input, World world, PlayerCharacter client) {
        Room room = worldManager.getCurrentRoom(client);
        List<String> allPlayersInRoom = clientRegistry.getAllPlayersInRoom(room);
        List<String> allNpcsInRoom = clientRegistry.getAllNpcsInRoom(room);
        return Optional.of(room.getTerminalFormattedText(allPlayersInRoom, allNpcsInRoom));
    }

    @Override
    public String getHelpText() {
        return "Displays a description of your current location";
    }
}
