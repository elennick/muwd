package com.wgg.muwd.world;

import com.wgg.muwd.util.StreamUtil;

import java.util.List;
import java.util.Optional;

public class World {

    private String name;
    private String motd;
    private List<Room> rooms;
    private List<String> enabledCommands;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotd() {
        return motd;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<String> getEnabledCommands() {
        return enabledCommands;
    }

    public void setEnabledCommands(List<String> enabledCommands) {
        this.enabledCommands = enabledCommands;
    }

    public Optional<Room> getRoomById(Long id) {
        Room room = null;

        try {
            room = rooms.stream().filter(r -> r.getId().equals(id)).collect(StreamUtil.singletonCollector());
        } catch (IllegalStateException ignored) {
        }

        if (null == room) {
            return Optional.empty();
        } else {
            return Optional.of(room);
        }
    }

    @Override
    public String toString() {
        return "World{" +
                "name='" + name + '\'' +
                ", motd='" + motd + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
