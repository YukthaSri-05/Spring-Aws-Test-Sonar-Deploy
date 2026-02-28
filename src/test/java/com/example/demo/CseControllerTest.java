package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CseControllerTest {

    @Autowired
    CseController c;

    @Test
    void testAddition() {
        int result = c.addCSE(10, 20);
        assertEquals(30, result);
    }

    @Test
    void testRollNoMessage() {
        String message = c.getRollNoMessage();
        assertEquals("After performing the addition, the final output is 30. My roll number is 23MH1A05N8.", message);
    }
}