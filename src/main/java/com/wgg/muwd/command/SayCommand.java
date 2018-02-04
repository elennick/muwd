package com.wgg.muwd.command;

import com.wgg.muwd.client.ClientRegistry;
import com.wgg.muwd.client.PlayerCharacter;
import com.wgg.muwd.util.WebsocketUtil;
import com.wgg.muwd.world.World;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SayCommand extends Command {

    @Autowired
    private ClientRegistry clientRegistry;

    @Autowired
    private WebsocketUtil util;

    @Override
    public String getCommandValue() {
        return "say";
    }

    @Override
    public Optional<String> getResponse(String[] input, World world, PlayerCharacter client) {
        final String messageForOthers = client.getName() + " says \"" + input[1] + "\"";
        final String messageForThisClient = "You say \"" + input[1] + "\"";

        Long currentRoom = client.getCurrentRoom();
        List<PlayerCharacter> allPlayersInRoom = clientRegistry.getAllPlayersInRoom(currentRoom);
        for (PlayerCharacter player : allPlayersInRoom) {
            boolean notThisClient = !player.getWebSocketSessionId().equals(client.getWebSocketSessionId());
            if (notThisClient) {
                util.sendToSession(player.getWebSocketSessionId(), messageForOthers);
            }
        }

        return Optional.of(messageForThisClient);
    }

    @Override
    public String getHelpText() {
        return "Say something that only people in the same room will hear.";
    }

}
