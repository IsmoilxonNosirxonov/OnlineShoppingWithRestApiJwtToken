package uz.in.onlineshoppingwithrestapijwttoken.domain.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDto {

    @Pattern(regexp = "^[a-zA-Z0-9]{4,15}$",
            message = "name length should be at least 4 max 15 characters " +
                    "and it should only satisfy [a-zA-Z0-9] pattern")
    private String name;

    private String description;

    private Integer quantity;

    private Double price;

    private UUID categoryId;

}
