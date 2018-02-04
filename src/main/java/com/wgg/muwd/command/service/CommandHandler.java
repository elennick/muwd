package com.wgg.muwd.command.service;

import com.wgg.muwd.command.Command;
import com.wgg.muwd.controller.model.CommandWrapper;
import com.wgg.muwd.client.ClientRegistry;
import com.wgg.muwd.world.service.WorldManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CommandHandler {

    private CommandRegistry commandRegistry;

    private ClientRegistry clientRegistry;

    private WorldManager worldManager;

    @Autowired
    public CommandHandler(CommandRegistry commandRegistry, ClientRegistry clientRegistry, WorldManager worldManager) {
        this.commandRegistry = commandRegistry;
        this.clientRegistry = clientRegistry;
        this.worldManager = worldManager;
    }

    public Optional<String> handleCommandInput(CommandWrapper commandWrapper) {
        String inputText = commandWrapper.getCommand();
        String[] inputTextSplit = getInputTextSplitBySpaces(inputText);

        String commandValue = inputTextSplit[0];
        Optional<Command> commandOptional = commandRegistry.getCommandByValue(commandValue);

        Optional<String> responseOptional;
        if (isValidCommand(commandOptional)) {
            Command command = commandOptional.get();
            responseOptional = command.getResponse(
                    inputTextSplit,
                    worldManager.getCurrentlyLoadedWorld().get(),
                    commandWrapper.getClient());
        } else {
            responseOptional = Optional.of("Unrecognized command: '" + inputText + "'");
        }

        return responseOptional;
    }

    private boolean isValidCommand(Optional<Command> commandOptional) {
        List<String> listOfEnabledCommands = worldManager.getListOfEnabledCommands();
        boolean commandIsPresentInSystem = commandOptional.isPresent();
        boolean commandIsEnabledInWorld = commandIsPresentInSystem && listOfEnabledCommands.contains(commandOptional.get().getCommandValue());

        return commandIsPresentInSystem && commandIsEnabledInWorld;
    }

    private String[] getInputTextSplitBySpaces(String inputText) {
        String[] inputTextSplit = StringUtils.split(inputText, " ");
        if (null == inputTextSplit) {
            inputTextSplit = new String[]{inputText};
        }
        return inputTextSplit;
    }
}
