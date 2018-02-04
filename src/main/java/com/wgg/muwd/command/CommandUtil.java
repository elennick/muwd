package com.wgg.muwd.command;

import com.wgg.muwd.controller.model.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommandUtil {

    @Autowired
    private SimpMessagingTemplate template;

    public void sendToSession(String webSocketSessionId, String message) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor
                .create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(webSocketSessionId);
        headerAccessor.setLeaveMutable(true);

        template.convertAndSendToUser(
                webSocketSessionId,
                "/topic/message", new ResponseWrapper(message),
                headerAccessor.getMessageHeaders());
    }
}
