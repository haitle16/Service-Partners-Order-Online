package com.rachnicrice.spordering.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApplicationUserTest {
    ApplicationUser user;

    @Autowired
    ApplicationUserRepository repo;

    @BeforeEach
    public void setUp () {
        user = new ApplicationUser("Name", "password", "email@gmail.com", "2533333333","jon", "Doe", "1243", "business", false);
    }

    @Test
    public void testGetUsername () {
        assertEquals("Name", user.getUsername());
    }

    @Test
    public void testGetPassword () {
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testGetEmail () {
        assertEquals("email@gmail.com", user.getEmail());
    }

    @Test
    public void testGetPhone () {
        assertEquals("2533333333", user.getPhone());
    }

    @Test
    public void testGetFirstName () {
        assertEquals("jon", user.getFirstName());
    }

    @Test
    public void testGetLastName () {
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void testGetSpCustomer_number () {
        assertEquals("1243", user.getSpCustomer_number());
    }

    @Test
    public void testGetBusinessName () {
        assertEquals("business", user.getBusinessName());
    }

    @Test
    public void testGetAdmin () {
        assertEquals(false, user.getAdmin());
    }
}
