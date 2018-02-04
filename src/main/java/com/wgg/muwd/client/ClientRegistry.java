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

    public List<String> getPlayerClientNames() {
        return pcRegistry.values().stream()
                .map(PlayerCharacter::getName)
                .collect(Collectors.toList());
    }

    public List<PlayerCharacter> getAllPlayersInRoom(Long roomId) {
        return pcRegistry.values().stream()
                .filter(c -> Objects.equals(c.getCurrentRoom(), roomId))
                .collect(Collectors.toList());
    }

    public List<String> getAllPlayerNamesInRoom(Room room) {
        return getAllPlayerNamesInRoom(room.getId());
    }

    public List<String> getAllPlayerNamesInRoom(Long roomId) {
        return pcRegistry.values().stream()
                .filter(c -> Objects.equals(c.getCurrentRoom(), roomId))
                .map(PlayerCharacter::getName)
                .collect(Collectors.toList());
    }

    public List<String> getAllNpcNamesInRoom(Room room) {
        return getAllNpcNamesInRoom(room.getId());
    }

    public List<String> getAllNpcNamesInRoom(Long roomId) {
        return npcRegistry.stream()
                .filter(c -> Objects.equals(c.getCurrentRoom(), roomId))
                .map(NonPlayerCharacter::getName)
                .collect(Collectors.toList());
    }

    public List<String> getAllClientNamesInRoom(Long roomId) {
        List<String> playersInRoom = getAllPlayerNamesInRoom(roomId);
        List<String> npcsInRoom = getAllNpcNamesInRoom(roomId);

        return Stream
                .concat(playersInRoom.stream(), npcsInRoom.stream())
                .collect(Collectors.toList());
    }

    public List<NonPlayerCharacter> getAllNpcs() {
        return npcRegistry;
    }

}
