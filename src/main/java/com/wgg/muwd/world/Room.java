package com.wgg.muwd.world;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class Room {

    private Long id;
    private String name;
    private String description;
    private Map<String, Long> directions;

}
