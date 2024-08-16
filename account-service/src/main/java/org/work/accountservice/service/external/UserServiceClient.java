// UserServiceClient.java
package org.work.accountservice.service.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.work.accountservice.model.external.UserDto;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceClient {

    private final WebClient.Builder webClientBuilder;

    public Mono<UserDto> getUserById(Long id) {
        String url = "http://user-service:8080/api/users/" + id;
        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(UserDto.class);
    }
}

/*
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserServiceClient {

    private final RestTemplate restTemplate;

    public UserDto getUserById(Long id) {
        String url = "http://user-service:8080/api/users/" + id;
        return restTemplate.getForObject(url, UserDto.class);
    }
}
*/