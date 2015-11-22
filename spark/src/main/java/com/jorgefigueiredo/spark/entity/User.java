package com.jorgefigueiredo.spark.entity;

import java.util.UUID;

public final class User {

    private final UUID id;
    private final String username;

    private final String email;

    public User(UUID id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }


    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("%s-%s-%s", id.toString(), username, email);
    }

}
