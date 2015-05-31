package com.wgg.muwd.controller.model;

public class ResponseWrapper {
    private String content;

    public ResponseWrapper(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
