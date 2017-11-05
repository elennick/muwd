package com.wgg.muwd.command;

import com.wgg.muwd.websocket.Client;
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
        Long currentRoom = client.getCurrentRoom();
        Optional<Room> roomOptional = worldManager.getRoomById(currentRoom);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            String response = "<span style='color:green;'>" + room.getName() + "<br/>"
                    + "<span style='color:white;'>" + room.getDescription() + "<br/>"
                    + "<span style='color:cornflowerblue;'>" + room.getDirections() + "<br/>";
            return Optional.of(response);
        } else {
            return Optional.of("You are surrounded by empty darkness...");
        }
    }

    @Override
    public String getHelpText() {
        return "Displays a description of your current location";
    }
}
