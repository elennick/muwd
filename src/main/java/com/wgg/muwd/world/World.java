package com.wgg.muwd.world;

import java.util.List;

public class World {

    private String name;
    private String motd;
    private List<Room> rooms;

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

    @Override
    public String toString() {
        return "World{" +
                "name='" + name + '\'' +
                ", motd='" + motd + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
