package com.wgg.muwd.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class WebSocketConnectHandler implements ApplicationListener<SessionConnectedEvent> {

    @Autowired
    private ClientRegistry clientRegistry;

    @Override
    public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
        Message<byte[]> message = sessionConnectedEvent.getMessage();
        MessageHeaders headers = message.getHeaders();
        String simpSessionId = (String)headers.get("simpSessionId");

        Client client = new Client(simpSessionId, 1L);
        clientRegistry.put(simpSessionId, client);
        System.out.println("added client -> " + simpSessionId);
    }
}
