package com.wgg.muwd.commands;

import com.wgg.muwd.service.CommandRegistry;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;

@Component
public class HelpCommand extends Command {

    @Override
    public String getCommandValue() {
        return "help";
    }

    @Override
    public String getResponse(String[] input, CommandRegistry commandRegistry) {
        StringBuilder response = new StringBuilder();

        response.append("List of available commands: ");

        Collection<Command> allCommands = commandRegistry.getAllCommands();
        String commaDelimitedStringOfAllCommands = StringUtils.collectionToDelimitedString(allCommands, ", ");
        response.append(commaDelimitedStringOfAllCommands);

        return response.toString();
    }
}
