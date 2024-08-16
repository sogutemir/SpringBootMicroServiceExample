package org.work.accountservice.service;

import org.work.accountservice.model.dto.AccountDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AccountService {
    Mono<AccountDto> createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAllAccounts();
    AccountDto updateAccount(Long id, AccountDto accountDto);
    void deleteAccount(Long id);
    AccountDto getAccountByUserId(Long userId);
}
