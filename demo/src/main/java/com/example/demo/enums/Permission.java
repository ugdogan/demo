package com.example.demo.enums;

public enum Permission {

    TODO_READ("todos:read"),
    TODO_WRITE("todos:write"),
    USERS_READ("users:read"),
    USERS_WRITE("users:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
