package com.wgg.muwd.service;

import com.wgg.muwd.commands.Command;
import com.wgg.muwd.controller.model.CommandMessage;
import com.wgg.muwd.controller.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CommandHandler {

    @Autowired
    private CommandRegistry commandRegistry;

    public ResponseMessage handleInput(CommandMessage commandMessage) {
        String inputText = commandMessage.getText();

        String[] inputTextSplit = StringUtils.split(inputText, " ");

        String commandValue;
        if(null != inputTextSplit) {
            commandValue = inputTextSplit[0];
        } else {
            commandValue = inputText;
        }

        Command command = commandRegistry.getCommandByValue(commandValue);

        String response;
        if(null == command) {
            response = "Unrecognized command: '" + commandValue + "'";
        } else {
            response = command.getResponse(inputText, commandRegistry);
        }

        return new ResponseMessage(response);
    }
}
