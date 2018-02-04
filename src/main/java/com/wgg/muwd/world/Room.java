package com.wgg.muwd.world;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

@Data
@NoArgsConstructor
public class Room {

    //TODO make it so rooms can have items in them that you can interact with

    private Long id;
    private String name;
    private String description;
    private Map<String, Long> directions;

    public String getTerminalFormattedText() {
        return getTerminalFormattedText(null, null);
    }

    public String getTerminalFormattedText(@Nullable List<String> playersInRoom) {
        return getTerminalFormattedText(playersInRoom, null);
    }

    public String getTerminalFormattedText(@Nullable List<String> playersInRoom, @Nullable List<String> npcsInRoom) {
        String directions = this.getDirections().isEmpty() ? "none" :
                this.getDirections().keySet().stream().collect(Collectors.joining(", "));

        String response = "<span style='color:orange;'>" + this.getName() + "</span><br/>"
                + "<span style='color:white;'>" + this.getDescription() + "</span><br/>"
                + "Exits: <span style='color:cornflowerblue;'>" + directions + "</span><br/>";

        if (null != playersInRoom && playersInRoom.size() > 0) {
            String clients = playersInRoom.stream().collect(Collectors.joining(", "));
            response += "Players here: <span style='color:cornflowerblue;'>" + clients + "</span><br/>";
        }

        if (null != npcsInRoom && npcsInRoom.size() > 0) {
            String clients = npcsInRoom.stream().collect(Collectors.joining(", "));
            response += "NPCs here: <span style='color:cornflowerblue;'>" + clients + "</span><br/>";
        }

        return response;
    }
}
