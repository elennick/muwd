package com.wgg.muwd.util;

import com.wgg.muwd.client.ClientRegistry;
import com.wgg.muwd.client.PlayerCharacter;
import com.wgg.muwd.controller.model.ResponseWrapper;
import com.wgg.muwd.world.Room;
import com.wgg.muwd.world.service.WorldManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

@Component
public class WebsocketUtil {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ClientRegistry clientRegistry;

    @Autowired
    private WorldManager worldManager;

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

    public void sendToAllInRoomButSelf(Long roomId, String message, PlayerCharacter self) {
        List<PlayerCharacter> allPlayersInRoom = clientRegistry.getAllPlayersInRoom(roomId);
        for (PlayerCharacter player : allPlayersInRoom) {
            boolean notSelf = !player.getWebSocketSessionId().equals(self.getWebSocketSessionId());
            if(notSelf) {
                sendToSession(player.getWebSocketSessionId(), message);
            }
        }
    }

    @Nullable
    public String getRoomDescription(Long roomId) {
        Optional<Room> roomOptional = worldManager.getRoomById(roomId);
        if (!roomOptional.isPresent()) {
            return null;
        }

        Room room = roomOptional.get();
        List<String> allPlayersInRoom = clientRegistry.getAllPlayerNamesInRoom(room);
        List<String> allNpcsInRoom = clientRegistry.getAllNpcNamesInRoom(room);
        return room.getTerminalFormattedText(allPlayersInRoom, allNpcsInRoom);
    }
}
