package com.wgg.muwd.world;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Room {

    private Long id;
    private String name;
    private String description;
    private Map<String, Long> directions;

    public String getTerminalFormattedText() {
        String directions = this.getDirections().isEmpty() ? "none" :
                this.getDirections().keySet().stream().collect(Collectors.joining(","));

        return "<span style='color:green;'>" + this.getName() + "<br/>"
                + "<span style='color:white;'>" + this.getDescription() + "<br/>"
                + "Exits: <span style='color:cornflowerblue;'>" + directions + "<br/>";
    }
}
