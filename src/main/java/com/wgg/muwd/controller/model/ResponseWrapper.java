package com.wgg.muwd.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ResponseWrapper {

    @NonNull
    private String content;

}
