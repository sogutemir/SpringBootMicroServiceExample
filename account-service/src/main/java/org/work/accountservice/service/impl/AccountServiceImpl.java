// AccountServiceImpl.java
package org.work.accountservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.work.accountservice.exception.ResourceNotFoundException;
import org.work.accountservice.model.dto.AccountDto;
import org.work.accountservice.model.entity.Account;
import org.work.accountservice.model.external.UserDto;
import org.work.accountservice.model.mapper.AccountMapper;
import org.work.accountservice.repository.AccountRepository;
import org.work.accountservice.service.AccountService;
import org.work.accountservice.service.external.UserServiceClient;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserServiceClient userServiceClient;
    private final AccountMapper accountMapper = new AccountMapper();

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        UserDto user = userServiceClient.getUserById(accountDto.getUserId());
        if (Objects.isNull(user)) {
            throw new ResourceNotFoundException("User not found on the server with id: " + accountDto.getUserId());
        }
        Account account = accountMapper.convertToEntity(accountDto);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.convertToDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
        return accountMapper.convertToDto(account);
    }

    @Override
    public AccountDto getAccountByUserId(Long userId) {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No account found for userId: " + userId));
        return accountMapper.convertToDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(accountMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto updateAccount(Long id, AccountDto accountDto) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
        Account updatedAccount = accountMapper.convertToEntity(accountDto);
        updatedAccount.setId(existingAccount.getId());
        Account savedAccount = accountRepository.save(updatedAccount);
        return accountMapper.convertToDto(savedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        try {
            Account account = accountRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
            accountRepository.delete(account);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to delete account with id: " + id, ex);
        }
    }


}

/*
    @Override
    public Mono<AccountDto> createAccount(AccountDto accountDto) {
        return userServiceClient.getUserById(accountDto.getUserId())
                .flatMap(user -> {
                    if (Objects.isNull(user)) {
                        return Mono.error(new ResourceNotFoundException("User not found on the server with id: " + accountDto.getUserId()));
                    }
                    Account account = accountMapper.convertToEntity(accountDto);
                    Account savedAccount = accountRepository.save(account);
                    return Mono.just(accountMapper.convertToDto(savedAccount));
                });
    }
    */

//    @Override
//    public AccountDto createAccount(AccountDto accountDto) {
//
//        UserDto user = userServiceClient.getUserById(accountDto.getUserId());
//        if (Objects.isNull(user)) {
//            throw new ResourceNotFoundException("User not found on the server with id: " + accountDto.getUserId());
//        }
//
//        Account account = accountMapper.convertToEntity(accountDto);
//        Account savedAccount = accountRepository.save(account);
//        return accountMapper.convertToDto(savedAccount);
//    }