package com.wgg.muwd.util;

import com.wgg.muwd.client.ClientRegistry;
import com.wgg.muwd.client.PlayerCharacter;
import com.wgg.muwd.controller.model.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WebsocketUtil {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ClientRegistry clientRegistry;

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

    public void sendToAllInRoom(Long roomId, String message) {
        List<PlayerCharacter> allPlayersInRoom = clientRegistry.getAllPlayersInRoom(roomId);
        for (PlayerCharacter player : allPlayersInRoom) {
            sendToSession(player.getWebSocketSessionId(), message);
        }
    }
}
