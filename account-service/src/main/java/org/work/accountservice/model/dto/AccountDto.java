package org.work.accountservice.model.dto;

import lombok.*;

/**
 * DTO for {@link org.work.accountservice.model.entity.Account}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    Long id;
    String accountNumber;
    String accountHolderName;
    Long userId;
}