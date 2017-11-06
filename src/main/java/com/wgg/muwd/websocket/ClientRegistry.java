package com.wgg.muwd.websocket;

import com.google.common.collect.Lists;
import com.wgg.muwd.world.Room;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class ClientRegistry {

    private Map<String, Client> registry;

    public ClientRegistry() {
        registry = new ConcurrentHashMap<>();
    }

    public void put(String sessionId, Client value) {
        registry.put(sessionId, value);
    }

    public Client get(String sessionId) {
        return registry.get(sessionId);
    }

    public void remove(String sessionId) {
        registry.remove(sessionId);
    }

    public List<Client> getClients() {
        return Lists.newArrayList(registry.values());
    }

    public List<String> getAllClientsInRoom(Room room) {
        return getAllClientsInRoom(room.getId());
    }

    public List<String> getAllClientsInRoom(Long roomId) {
        return registry.values().stream()
                .filter(c -> Objects.equals(c.getCurrentRoom(), roomId))
                .map(Client::getName)
                .collect(Collectors.toList());
    }

}
