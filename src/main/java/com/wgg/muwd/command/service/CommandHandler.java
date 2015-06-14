package com.wgg.muwd.command.service;

import com.wgg.muwd.command.Command;
import com.wgg.muwd.controller.model.CommandWrapper;
import com.wgg.muwd.controller.model.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class CommandHandler {

    @Autowired
    private CommandRegistry commandRegistry;

    public String handleCommandInput(CommandWrapper commandWrapper) {
        String inputText = commandWrapper.getCommand();
        String[] inputTextSplit = getInputTextSplitBySpaces(inputText);

        String commandValue = inputTextSplit[0];
        Optional<Command> commandOptional = commandRegistry.getCommandByValue(commandValue);

        String response;
        if(commandOptional.isPresent()) {
            Command command = commandOptional.get();
            response = command.getResponse(inputTextSplit, commandRegistry);
        } else {
            response = "Unrecognized command: '" + inputText + "'";
        }

        return response;
    }

    private String[] getInputTextSplitBySpaces(String inputText) {
        String[] inputTextSplit = StringUtils.split(inputText, " ");
        if (null == inputTextSplit) {
            inputTextSplit = new String[]{inputText};
        }
        return inputTextSplit;
    }
}
