package com.wgg.muwd.controller;

import com.wgg.muwd.command.service.CommandHandler;
import com.wgg.muwd.controller.model.CommandWrapper;
import com.wgg.muwd.controller.model.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @Autowired
    private CommandHandler commandHandler;

    @MessageMapping("/command")
    @SendToUser("/topic/message")
    public ResponseWrapper message(CommandWrapper commandWrapper) throws Exception {
        String response = commandHandler.handleCommandInput(commandWrapper);
        return new ResponseWrapper(response);
    }

}
