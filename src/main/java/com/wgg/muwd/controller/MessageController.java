package com.wgg.muwd.controller;

import com.wgg.muwd.command.service.CommandHandler;
import com.wgg.muwd.controller.model.CommandWrapper;
import com.wgg.muwd.controller.model.ResponseWrapper;
import com.wgg.muwd.websocket.Client;
import com.wgg.muwd.websocket.ClientRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private ClientRegistry clientRegistry;

    @MessageMapping("/command")
    @SendToUser("/topic/message")
    public ResponseWrapper message(
            @Payload CommandWrapper commandWrapper,
            SimpMessageHeaderAccessor headerAccessor) throws Exception {

        Client client = clientRegistry.get(headerAccessor.getHeader("simpSessionId").toString());
        commandWrapper.setClient(client);
        System.out.println("commandWrapper = " + commandWrapper);

        String response = commandHandler.handleCommandInput(commandWrapper);
        return new ResponseWrapper(response);
    }
}
