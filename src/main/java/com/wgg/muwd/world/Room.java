package com.wgg.muwd.world;

import java.util.Map;

public class Room {

    private Long id;
    private String name;
    private String description;
    private Map<String, Long> directions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Long> getDirections() {
        return directions;
    }

    public void setDirections(Map<String, Long> directions) {
        this.directions = directions;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", directions=" + directions +
                '}';
    }

}
