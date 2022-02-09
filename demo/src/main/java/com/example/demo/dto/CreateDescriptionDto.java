package com.example.demo.dto;

import javax.validation.constraints.NotBlank;

public class CreateDescriptionDto {

    @NotBlank(message = "description is mandatory")
    private String description;

    public CreateDescriptionDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
