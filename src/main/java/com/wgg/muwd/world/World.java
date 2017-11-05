package com.wgg.muwd.world;

import com.wgg.muwd.util.StreamUtil;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class World {

    private String name;
    private String motd;
    private String startingRoom;
    private List<Room> rooms;
    private List<String> enabledCommands;

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

}
