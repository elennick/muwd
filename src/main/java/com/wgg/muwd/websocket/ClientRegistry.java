package com.wgg.muwd.websocket;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ClientRegistry {

    Map<String, Client> registry;

    public ClientRegistry() {
        registry = new ConcurrentHashMap<>();
    }

    public void put(String key, Client value) {
        registry.put(key, value);
    }

    public Client get(String key) {
        return registry.get(key);
    }

    public void remove(String key) {
        registry.remove(key);
    }

    public List<Client> getClients() {
        return Lists.newArrayList(registry.values());
    }

}
