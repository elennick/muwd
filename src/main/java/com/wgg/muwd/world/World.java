package com.wgg.muwd.world;

import com.wgg.muwd.util.StreamUtil;

import java.util.List;
import java.util.Optional;

public class World {

    private String name;
    private String motd;
    private String startingRoom;
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

    public String getStartingRoom() {
        return startingRoom;
    }

    public void setStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
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
        final StringBuilder sb = new StringBuilder("World{");
        sb.append("name='").append(name).append('\'');
        sb.append(", motd='").append(motd).append('\'');
        sb.append(", startingRoom='").append(startingRoom).append('\'');
        sb.append(", rooms=").append(rooms);
        sb.append(", enabledCommands=").append(enabledCommands);
        sb.append('}');
        return sb.toString();
    }
}
