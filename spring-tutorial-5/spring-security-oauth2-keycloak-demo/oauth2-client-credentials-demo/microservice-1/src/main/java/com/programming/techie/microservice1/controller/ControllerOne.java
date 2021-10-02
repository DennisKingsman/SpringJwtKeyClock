package com.programming.techie.microservice1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/mcs1")
public class ControllerOne {

    private RestTemplate restTemplate;
    private WebClient webClient;

    @Autowired
    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public String helloRestTemplate() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt.getTokenValue());
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8084/mcs2/hello",
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);
        return "Hello mcs 1. Message from mcs 2 is " + response.getBody();
    }

    @GetMapping("/hello/web-client")
    @ResponseStatus(HttpStatus.OK)
    public String helloWebClient() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String response = webClient.get()
                .uri("http://localhost:8084/mcs2/hello")
                .headers(header -> header.setBearerAuth(jwt.getTokenValue())) //Consumer<HttpHeaders>
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return "Hello mcs 1. Message from mcs 2 is " + response;
    }

}
