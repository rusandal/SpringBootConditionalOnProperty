package com.example.springparameters;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringParametersApplicationTests {
    private static GenericContainer<?> devapp = new GenericContainer<>("devapp");
    private static GenericContainer<?> prodapp = new GenericContainer<>("prodapp");

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        prodapp.start();
        devapp.start();
    }

    @Test
    void devAnswer() {
        //Integer portDev = devapp.getMappedPort(8080);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(8080)+"/profile", String.class);
        //System.out.println(forEntity.getBody());
        assert (forEntity.getBody().equals("Current profile is dev"));
    }

    @Test
    void prodAnswer(){
        ResponseEntity<String> forEntity2 = restTemplate.getForEntity("http://localhost:" + prodapp.getMappedPort(8081)+"/profile", String.class);
        assert (forEntity2.getBody().equals("Current profile is production"));
        //System.out.println(forEntity2.getBody());
    }


}
