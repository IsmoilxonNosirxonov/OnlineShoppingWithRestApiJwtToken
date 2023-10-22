package uz.in.onlineshoppingwithrestapijwttoken.domain.dto;

import lombok.*;
import uz.in.onlineshoppingwithrestapijwttoken.domain.Enum.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto {

    private UUID userId;

    private List<UUID> orderItemIds=new ArrayList<>();

    private Double totalPrice;

    private OrderStatus status;

}
