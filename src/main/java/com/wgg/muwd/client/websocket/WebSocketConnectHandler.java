package com.wgg.muwd.client.websocket;

import com.wgg.muwd.client.ClientRegistry;
import com.wgg.muwd.client.PlayerCharacter;
import com.wgg.muwd.controller.model.ResponseWrapper;
import com.wgg.muwd.util.NamePicker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Slf4j
@Component
public class WebSocketConnectHandler implements ApplicationListener<SessionConnectedEvent> {

    @Autowired
    private ClientRegistry clientRegistry;

    @Autowired
    private NamePicker namePicker;

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
        Message<byte[]> message = sessionConnectedEvent.getMessage();
        MessageHeaders headers = message.getHeaders();
        String simpSessionId = (String) headers.get("simpSessionId");

        String randomName = namePicker.getRandomName();
        PlayerCharacter client = new PlayerCharacter(simpSessionId, randomName, 1L);
        clientRegistry.putPlayer(simpSessionId, client);
        log.info("Added PlayerCharacter to client registry: {}", simpSessionId);

        String broadcast = client.getName() + " has joined the world!";
        template.convertAndSend("/topic/message", new ResponseWrapper(broadcast));

        //TODO send this user that just logged in the MOTD and show them the room theyre in
    }
}
