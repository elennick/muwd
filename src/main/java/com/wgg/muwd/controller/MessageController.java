package com.wgg.muwd.controller;

import com.wgg.muwd.controller.model.CommandMessage;
import com.wgg.muwd.controller.model.ResponseMessage;
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
    public ResponseMessage message(CommandMessage commandMessage) throws Exception {
        return commandHandler.handleInput(commandMessage);
    }

}
