package com.wgg.muwd.command;

import com.wgg.muwd.client.ClientRegistry;
import com.wgg.muwd.client.PlayerCharacter;
import com.wgg.muwd.util.WebsocketUtil;
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

    @Autowired
    private WebsocketUtil websocketUtil;

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
        String description = websocketUtil.getRoomDescription(room.getId());
        return Optional.of(description);
    }

    @Override
    public String getHelpText() {
        return "Displays a description of your current location";
    }
}
