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

public class MoveCommand extends Command {

    @Autowired
    private WorldManager worldManager;

    @Autowired
    private ClientRegistry clientRegistry;

    @Autowired
    private WebsocketUtil websocketUtil;

    @Override
    public String getCommandValue() {
        return "move";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("go", "walk");
    }

    @Override
    public Optional<String> getResponse(String[] input, World world, PlayerCharacter client) {
        if (input.length <= 1) {
            return Optional.of("What direction?");
        }

        String direction = input[1].toLowerCase();

        Room currentRoom = worldManager.getCurrentRoom(client);
        Long roomToMoveTo = currentRoom.getDirections().get(direction);

        if (null != roomToMoveTo) {
            final String roomLeavingMessage = client.getName() + " left the room.";
            websocketUtil.sendToAllInRoomButSelf(currentRoom.getId(), roomLeavingMessage, client);

            final String roomEnteringMessage = client.getName() + " entered the room.";
            websocketUtil.sendToAllInRoomButSelf(roomToMoveTo, roomEnteringMessage, client);

            client.setCurrentRoom(roomToMoveTo);
            Room newRoom = worldManager.getCurrentRoom(client);

            List<String> allPlayersInRoom = clientRegistry.getAllPlayerNamesInRoom(newRoom);
            List<String> allNpcsInRoom = clientRegistry.getAllNpcNamesInRoom(newRoom);
            String newRoomText = newRoom.getTerminalFormattedText(allPlayersInRoom, allNpcsInRoom);

            String response = "Moving " + direction + "...<br/>" + newRoomText;
            return Optional.of(response);
        } else {
            return Optional.of("You can't go that direction!");
        }
    }

    @Override
    public String getHelpText() {
        return "Moves yourself in a direction (ie: 'move west', 'move up')";
    }
}
