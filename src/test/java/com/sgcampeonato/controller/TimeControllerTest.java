package com.sgcampeonato.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TimeControllerTest {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void findTest() {
        String url = String.format("http://localhost:%s/api/time/list?page=1", port);
        ResponseEntity<String> response = template.getForEntity(url, String.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}