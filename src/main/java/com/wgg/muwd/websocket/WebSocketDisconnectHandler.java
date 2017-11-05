package com.wgg.muwd.websocket;

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

        Client client = clientRegistry.get(simpSessionId);
        String broadcast = client.getName() + " has left the world!";
        template.convertAndSend("/topic/message", new ResponseWrapper(broadcast));

        clientRegistry.remove(simpSessionId);
        log.info("Removed Client from client registry: {}", simpSessionId);
    }

}
