package com.wgg.muwd.service;

import com.wgg.muwd.commands.Command;
import com.wgg.muwd.controller.model.CommandWrapper;
import com.wgg.muwd.controller.model.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CommandHandler {

    @Autowired
    private CommandRegistry commandRegistry;

    public ResponseWrapper handleCommandInput(CommandWrapper commandWrapper) {
        String inputText = commandWrapper.getCommand();
        String[] inputTextSplit = getInputTextSplitBySpaces(inputText);

        String commandValue = inputTextSplit[0];
        Command command = commandRegistry.getCommandByValue(commandValue);

        String unrecognizedCommandResponse = "Unrecognized command: '" + inputText + "'";
        String response = (null == command) ? unrecognizedCommandResponse : command.getResponse(inputTextSplit, commandRegistry);

        return new ResponseWrapper(response);
    }

    private String[] getInputTextSplitBySpaces(String inputText) {
        String[] inputTextSplit = StringUtils.split(inputText, " ");
        if (null == inputTextSplit) {
            inputTextSplit = new String[]{inputText};
        }
        return inputTextSplit;
    }
}
