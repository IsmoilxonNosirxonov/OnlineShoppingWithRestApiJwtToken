package uz.in.onlineshoppingwithrestapijwttoken.domain.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import uz.in.onlineshoppingwithrestapijwttoken.domain.Enum.UserRole;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$",
            message = "Full name length should be at least 5 max 20 characters " +
                    "and it should only satisfy [a-zA-Z0-9] pattern")
    private String fullName;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "email should only satisfy ^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$ pattern")
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,20}$",
            message = "password length should be at least 8 max 20 characters " +
                    "and it should only satisfy [a-zA-Z0-9] pattern")
    private String password;

    private String phoneNumber;

    private Double balance;

    private UserRole role;

}
