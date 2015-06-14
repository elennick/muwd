package com.wgg.muwd.controller;

import com.wgg.muwd.controller.model.ResponseWrapper;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class LoginController {

    @MessageMapping("/login")
    @SendTo("/topic/loginresponse")
    public ResponseWrapper login() throws Exception {
        String uuidString = UUID.randomUUID().toString();
        return new ResponseWrapper(uuidString);
    }

}
