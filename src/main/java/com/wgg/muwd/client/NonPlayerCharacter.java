package com.wgg.muwd.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
public class NonPlayerCharacter extends Client {

    @NonNull
    private Long id;

    @NonNull
    private String description;

    @NonNull
    private List<String> dialogue;

    public NonPlayerCharacter(Long id, String name, String description, List<String> dialogue) {
        super(name, 1L);
        this.id = id;
        this.description = description;
        this.dialogue = dialogue;
    }

}
