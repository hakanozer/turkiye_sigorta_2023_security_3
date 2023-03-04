package com.works.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.props.Auth;
import com.works.props.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DummyService {

    final ObjectMapper objectMapper;
    final RestTemplate restTemplate;

    public ResponseEntity auth() {
        try {
            String url = "https://dummyjson.com/auth/login";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("token", "asdasdas");

            JWT jwt = new JWT();
            jwt.setUsername("kminchelle");
            jwt.setPassword("0lelplR");
            String stObj = objectMapper.writeValueAsString(jwt);
            HttpEntity httpEntity = new HttpEntity(stObj, headers);

            ResponseEntity<Auth> authResponseEntity = restTemplate.postForEntity(url, httpEntity, Auth.class);
            Auth auth = authResponseEntity.getBody();
            System.out.println( auth.getToken() );
            return authResponseEntity;

        }catch (Exception ex) {}
        return new ResponseEntity("", HttpStatus.BAD_REQUEST);
    }

}
