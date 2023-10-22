package uz.in.onlineshoppingwithrestapijwttoken.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Positive
    private Integer quantity;

    @Column(nullable = false)
    @Positive
    private Double price;

    @ManyToOne()
    private Category category;
}
