package com.wgg.muwd.websocket;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

}
