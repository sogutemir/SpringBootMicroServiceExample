package org.work.accountservice.service.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.work.accountservice.model.external.UserDto;

@Service
@RequiredArgsConstructor
public class UserServiceClient {

    private final RestTemplate restTemplate;

    public UserDto getUserById(Long id) {
        String url = "http://user-service:8080/api/users/" + id;
        return restTemplate.getForObject(url, UserDto.class);
    }
}