package com.example.notesapp.dto;

public class CreateNoteRequest {
    private String title;
    private String content;
    private String userId;

    public CreateNoteRequest() {}

    public CreateNoteRequest(String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}