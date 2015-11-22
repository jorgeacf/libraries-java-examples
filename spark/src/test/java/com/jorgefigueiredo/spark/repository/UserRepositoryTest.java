package com.jorgefigueiredo.spark.repository;

import com.jorgefigueiredo.spark.entity.User;
import junit.framework.TestCase;

import java.util.Arrays;

public class UserRepositoryTest extends TestCase {


    public void testRepository() {

        IUserRepository userRepository = new UserRepository();

        User[] users1 = userRepository.getAll();
        User[] users2 = userRepository.getAll();

        assertTrue(Arrays.equals(users1, users2));
        assertFalse(users1 == users2);
    }

}
