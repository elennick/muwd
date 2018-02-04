package com.wgg.muwd.command;

import com.wgg.muwd.client.ClientRegistry;
import com.wgg.muwd.client.PlayerCharacter;
import com.wgg.muwd.world.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class WhoCommand extends Command {

    @Autowired
    private ClientRegistry clientRegistry;

    @Override
    public String getCommandValue() {
        return "who";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("online", "users");
    }

    @Override
    public Optional<String> getResponse(String[] input, World world, PlayerCharacter client) {
        List<String> names = clientRegistry.getPlayerClientNames();
        String listOfNames = StringUtils.collectionToDelimitedString(names, ", ");
        String response = "<span style=\"color: green;\">Players online:</span>&nbsp;" + listOfNames;
        return Optional.of(response);
    }

    @Override
    public String getHelpText() {
        return "See who is online.";
    }
}
