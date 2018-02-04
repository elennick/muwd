package com.wgg.muwd.command;

import com.wgg.muwd.client.PlayerCharacter;
import com.wgg.muwd.controller.model.ResponseWrapper;
import com.wgg.muwd.world.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BroadcastCommand extends Command {

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public String getCommandValue() {
        return "broadcast";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("yell");
    }

    @Override
    public Optional<String> getResponse(String[] input, World world, PlayerCharacter client) {
        String broadcast = client.getName() + " yells \"" + input[1] + "\"";
        template.convertAndSend("/topic/message", new ResponseWrapper(broadcast));
        return Optional.empty();
    }

    @Override
    public String getHelpText() {
        return "Broadcasts a message to everyone in the world. Example: 'broadcast \"hey everyone!\"";
    }
}
