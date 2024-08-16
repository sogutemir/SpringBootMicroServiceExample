package org.work.userservice.model.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
