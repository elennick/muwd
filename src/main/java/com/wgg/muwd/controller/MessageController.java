package com.wgg.muwd.controller;

import com.wgg.muwd.controller.model.CommandWrapper;
import com.wgg.muwd.controller.model.ResponseWrapper;
import com.wgg.muwd.service.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @Autowired
    private CommandHandler commandHandler;

    @MessageMapping("/command")
    @SendTo("/topic/message")
    public ResponseWrapper message(CommandWrapper commandWrapper) throws Exception {
        return commandHandler.handleCommandInput(commandWrapper);
    }

}
