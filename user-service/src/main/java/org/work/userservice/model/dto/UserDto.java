package org.work.userservice.model.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link org.work.userservice.model.entity.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
}