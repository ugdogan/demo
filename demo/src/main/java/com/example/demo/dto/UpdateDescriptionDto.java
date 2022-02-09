package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateDescriptionDto {

    @NotNull
    private Long id;

    @NotBlank(message = "description is mandatory")
    private String description;

    @NotNull
    private Boolean isDone;

    public UpdateDescriptionDto() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}