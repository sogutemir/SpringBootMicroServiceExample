package org.work.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.work.userservice.exception.ResourceNotFoundException;
import org.work.userservice.model.dto.UserDto;
import org.work.userservice.model.entity.User;
import org.work.userservice.model.external.AccountDto;
import org.work.userservice.model.mapper.UserMapper;
import org.work.userservice.repository.UserRepository;
import org.work.userservice.service.UserService;
import org.work.userservice.service.external.AccountServiceClient;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AccountServiceClient accountServiceClient;

    private final UserMapper userMapper = new UserMapper();

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.convertToEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.convertToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.convertToDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userMapper.convertToEntity(userDto).setId(existingUser.getId());
        User updatedUser = userRepository.save(existingUser);
        return userMapper.convertToDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserByAccountId(Long accountId) {
        AccountDto accountDto = accountServiceClient.getAccountById(accountId);
        if (accountDto != null) {
            return userRepository.findById(accountDto.getUserId())
                    .map(userMapper::convertToDto)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with accountId: " + accountId));
        }
        throw new ResourceNotFoundException("No user found for accountId: " + accountId);
    }


}

//WebClient
/*
//@Override
//    public Mono<UserDto> getUserByAccountId(Long accountId) {
//        return accountServiceClient.getAccountById(accountId)
//                .flatMap(accountDto -> {
//                    if (accountDto != null) {
//                        return Mono.defer(() -> userRepository.findById(accountDto.getUserId())
//                                .map(userMapper::convertToDto)
//                                .map(Mono::just)
//                                .orElseGet(() -> Mono.error(new ResourceNotFoundException("User not found with accountId: " + accountId))));
//                    }
//                    return Mono.error(new ResourceNotFoundException("No user found for accountId: " + accountId));
//                });
//    }

 */

//RestTemplate
/*
//    @Override
//    public UserDto getUserByAccountId(Long accountId) {
//        AccountDto accountDto = accountServiceClient.getAccountById(accountId);
//        if(accountDto != null) {
//            return userRepository.findById(accountDto.getUserId())
//                    .map(userMapper::convertToDto)
//                    .orElseThrow(() -> new ResourceNotFoundException("User not found with accountId: " + accountId));
//        }
//        throw new ResourceNotFoundException("No user found for accountId: " + accountId);
//    }

 */