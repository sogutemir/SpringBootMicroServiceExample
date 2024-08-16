package org.work.userservice.service;

import org.work.userservice.model.dto.UserDto;
import reactor.core.publisher.Mono;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);

    Mono<UserDto> getUserByAccountId(Long accountId);

}
