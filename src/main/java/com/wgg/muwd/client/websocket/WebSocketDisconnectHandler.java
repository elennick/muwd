package com.wgg.muwd.client.websocket;

import com.wgg.muwd.client.ClientRegistry;
import com.wgg.muwd.client.PlayerCharacter;
import com.wgg.muwd.controller.model.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Component
public class WebSocketDisconnectHandler implements ApplicationListener<SessionDisconnectEvent> {

    @Autowired
    private ClientRegistry clientRegistry;

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        Message<byte[]> message = sessionDisconnectEvent.getMessage();
        MessageHeaders headers = message.getHeaders();
        String simpSessionId = (String) headers.get("simpSessionId");

        PlayerCharacter client = clientRegistry.getPlayerBySessionId(simpSessionId);
        String broadcast = client.getName() + " has left the world!";
        template.convertAndSend("/topic/message", new ResponseWrapper(broadcast));

        clientRegistry.removePlayerBySessionId(simpSessionId);
        log.info("Removed PlayerCharacter from client registry: {}", simpSessionId);
    }

}
