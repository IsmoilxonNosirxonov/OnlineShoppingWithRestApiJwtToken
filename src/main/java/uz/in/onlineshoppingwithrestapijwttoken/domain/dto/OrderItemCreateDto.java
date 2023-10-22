package uz.in.onlineshoppingwithrestapijwttoken.domain.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemCreateDto {

    private UUID productId;

    private Integer amount;

    private UUID userId;

}
