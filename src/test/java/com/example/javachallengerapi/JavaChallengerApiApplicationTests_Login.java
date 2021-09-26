package com.example.javachallengerapi;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@SpringBootTest
class JavaChallengerApiApplicationTests_Login {

    private static Logger log = Logger.getLogger(JavaChallengerApiApplicationTests_Login.class.getSimpleName());

    @BeforeAll
    static void setup(){
        log.info("@BeforeAll - executes once before all test methods in this class");

    }

    @BeforeEach
    void init() {
        log.info("@BeforeEach - executes before each test method in this class");
    }

    @AfterEach
    void tearDown() {
        log.info("@AfterEach - executed after each test method.");
    }

    @AfterAll
    static void done() {
        log.info("@AfterAll - executed after all test methods.");
    }

    @Test
    @Disabled("Not implemented yet")
    void contextLoads() {
    }

    @DisplayName("Single test successful")
    @Test
    void testSingleSuccessTest() {
        log.info("Success");
    }
}
