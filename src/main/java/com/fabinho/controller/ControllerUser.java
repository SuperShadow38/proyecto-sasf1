package com.fabinho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



import com.fabinho.model.User;

@RestController
@RequestMapping("/users")
public class ControllerUser {

    private final String EXTERNAL_API_URL = "https://jsonplaceholder.typicode.com/users";

    private final RestTemplate restTemplate;

    @Autowired
    public ControllerUser(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

   
    @GetMapping(produces="application/json")
    public ResponseEntity<?> getUsersFromExternalApi() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(EXTERNAL_API_URL, User[].class);
        User[] users = response.getBody();

        

        return ResponseEntity.ok(users);
    }

}


