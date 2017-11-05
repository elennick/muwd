package com.wgg.muwd.command;

import com.wgg.muwd.websocket.Client;
import com.wgg.muwd.websocket.ClientRegistry;
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
    public Optional<String> getResponse(String[] input, World world, Client client) {
        List<Client> clients = clientRegistry.getClients();
        String response = StringUtils.collectionToDelimitedString(clients, ", ");
        return Optional.of(response);
    }

    @Override
    public String getHelpText() {
        return "See who is online.";
    }
}
