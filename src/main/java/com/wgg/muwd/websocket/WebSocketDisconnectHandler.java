package com.wgg.muwd.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketDisconnectHandler implements ApplicationListener<SessionDisconnectEvent> {

    @Autowired
    private ClientRegistry clientRegistry;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        Message<byte[]> message = sessionDisconnectEvent.getMessage();
        MessageHeaders headers = message.getHeaders();
        String simpSessionId = (String)headers.get("simpSessionId");

        clientRegistry.remove(simpSessionId);
        System.out.println("removed client -> " + simpSessionId);
    }

}
