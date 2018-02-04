package com.wgg.muwd.client;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class NonPlayerCharacter extends Client {

    @NonNull
    private Long id;

    @NonNull
    private String description;

    @NonNull
    private List<String> dialogue;

    public NonPlayerCharacter() {}

    public NonPlayerCharacter(Long id, String name, String description, Long currentRoom, List<String> dialogue) {
        super(name, currentRoom);
        this.id = id;
        this.description = description;
        this.dialogue = dialogue;
    }
}
