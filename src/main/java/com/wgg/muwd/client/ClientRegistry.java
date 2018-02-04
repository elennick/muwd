package com.wgg.muwd.client;

import com.google.common.collect.Lists;
import com.wgg.muwd.world.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ClientRegistry {

    private Map<String, PlayerCharacter> pcRegistry;

    private List<NonPlayerCharacter> npcRegistry;

    public ClientRegistry() {
        pcRegistry = new ConcurrentHashMap<>();
        npcRegistry = new ArrayList<>();
    }

    public void putPlayer(String sessionId, PlayerCharacter value) {
        pcRegistry.put(sessionId, value);
    }

    public void putNpc(NonPlayerCharacter value) {
        npcRegistry.add(value);
    }

    public PlayerCharacter getPlayerBySessionId(String sessionId) {
        return pcRegistry.get(sessionId);
    }

    public void removePlayerBySessionId(String sessionId) {
        pcRegistry.remove(sessionId);
    }

    public List<PlayerCharacter> getPlayerClients() {
        return Lists.newArrayList(pcRegistry.values());
    }

    public List<String> getAllPlayersInRoom(Room room) {
        return getAllPlayersInRoom(room.getId());
    }

    public List<String> getAllPlayersInRoom(Long roomId) {
        return pcRegistry.values().stream()
                .filter(c -> Objects.equals(c.getCurrentRoom(), roomId))
                .map(PlayerCharacter::getName)
                .collect(Collectors.toList());
    }

    public List<String> getAllNpcsInRoom(Room room) {
        return getAllNpcsInRoom(room.getId());
    }

    public List<String> getAllNpcsInRoom(Long roomId) {
        return npcRegistry.stream()
                .filter(c -> Objects.equals(c.getCurrentRoom(), roomId))
                .map(NonPlayerCharacter::getName)
                .collect(Collectors.toList());
    }

    public List<String> getAllClientsInRoom(Long roomId) {
        List<String> playersInRoom = getAllPlayersInRoom(roomId);
        List<String> npcsInRoom = getAllNpcsInRoom(roomId);

        return Stream
                .concat(playersInRoom.stream(), npcsInRoom.stream())
                .collect(Collectors.toList());
    }

}
