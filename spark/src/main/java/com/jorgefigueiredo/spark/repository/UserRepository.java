package com.jorgefigueiredo.spark.repository;

import com.jorgefigueiredo.spark.entity.User;

import java.util.UUID;

public class UserRepository implements IUserRepository {

    private final User[] users = new User[]
            {
                    new User(UUID.randomUUID(), "jorgeacf", "jorgefigueiredo@outlook.com")
            };

    @Override
    public User[] getAll() {
        return users.clone();
    }

}
