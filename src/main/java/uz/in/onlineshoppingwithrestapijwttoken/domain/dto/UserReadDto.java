package uz.in.onlineshoppingwithrestapijwttoken.domain.dto;

import lombok.*;
import uz.in.onlineshoppingwithrestapijwttoken.domain.Enum.UserRole;


import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReadDto {

    private UUID id;

    private String fullName;

    private String email;

    private String phoneNumber;

    private Double balance;

    private UserRole role;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
