package me.timickb.noteapp.model.request;

import lombok.Data;

@Data
public class CategoryCreateRequest {
    private String title;
    private Long userId;

    public Long getUserId() {
        return userId;
    }
}
