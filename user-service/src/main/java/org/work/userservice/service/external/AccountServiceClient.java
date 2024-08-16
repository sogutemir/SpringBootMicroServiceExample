package org.work.userservice.service.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.work.userservice.model.external.AccountDto;

@Service
@RequiredArgsConstructor
public class AccountServiceClient {

    private final RestTemplate restTemplate;

    public AccountDto getAccountById(Long id) {
        String url = "http://account-service:8080/api/accounts/" + id;
        return restTemplate.getForObject(url, AccountDto.class);
    }
}