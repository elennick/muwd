package com.wgg.muwd.command;

import com.wgg.muwd.command.service.CommandRegistry;
import com.wgg.muwd.websocket.Client;
import com.wgg.muwd.websocket.ClientRegistry;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

public class WhoCommand extends Command {

    @Override
    public String getCommandValue() {
        return "who";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("online","users");
    }

    @Override
    public String getResponse(String[] input, CommandRegistry commandRegistry, ClientRegistry clientRegistry) {
        List<Client> clients = clientRegistry.getClients();
        return StringUtils.collectionToDelimitedString(clients, ", ");
    }

    @Override
    public String getHelpText() {
        return "See who is online.";
    }
}
