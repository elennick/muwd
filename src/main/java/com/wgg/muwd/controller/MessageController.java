package com.wgg.muwd.controller;

import com.wgg.muwd.client.PlayerCharacter;
import com.wgg.muwd.command.service.CommandHandler;
import com.wgg.muwd.controller.model.CommandWrapper;
import com.wgg.muwd.controller.model.ResponseWrapper;
import com.wgg.muwd.client.ClientRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Slf4j
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

        //TODO strip any incoming command input of html tags or anything that could cause injection issues

        PlayerCharacter client = clientRegistry.getPlayerBySessionId(headerAccessor.getHeader("simpSessionId").toString());
        commandWrapper.setClient(client);
        log.info("Handling Command: {}", commandWrapper);

        Optional<String> responseOptional = commandHandler.handleCommandInput(commandWrapper);
        return responseOptional.map(ResponseWrapper::new).orElse(null);
    }
}