package com.wgg.muwd.world;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class WorldTest {

    private World world;

    private Room room1;

    @Before
    public void setup() {
        world = new World();

        List<Room> rooms = new ArrayList<>();

        room1 = new Room();
        room1.setId(12345L);
        room1.setDescription("test desc");
        room1.setName("test name");

        Map<String, Long> directions = new HashMap<>();
        directions.put("west", 2L);
        room1.setDirections(directions);

        rooms.add(room1);
        world.setRooms(rooms);
    }

    @Test
    public void testGettingRoomByValidId() {
        Optional<Room> roomOptional = world.getRoomById(12345L);
        assertThat(roomOptional).isPresent();

        Room roomGotten = roomOptional.get();
        assertThat(roomGotten).isEqualTo(room1);
    }

    @Test
    public void testGettingRoomByInvalidId() {
        Optional<Room> roomOptional = world.getRoomById(54321L);
        assertThat(roomOptional).isEmpty();
    }

}
