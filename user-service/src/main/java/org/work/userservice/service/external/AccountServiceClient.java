// AccountServiceClient.java
package org.work.userservice.service.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.work.userservice.model.external.AccountDto;

@Service
@RequiredArgsConstructor
public class AccountServiceClient {

    private final RestClient restClient;

    public AccountDto getAccountById(Long id) {
        String url = "http://account-service:8080/api/accounts/" + id;
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(AccountDto.class);
    }
}

//WebClient
/*
//    private final WebClient.Builder webClientBuilder;
//
//    public Mono<AccountDto> getAccountById(Long id) {
//        String url = "http://account-service:8080/api/accounts/" + id;
//        return webClientBuilder.build()
//                .get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(AccountDto.class);
//    }

 */


//RestTemplate
/*
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AccountServiceClient {

    private final RestTemplate restTemplate;

    public AccountDto getAccountById(Long id) {
        String url = "http://account-service:8080/api/accounts/" + id;
        return restTemplate.getForObject(url, AccountDto.class);
    }
}
*/