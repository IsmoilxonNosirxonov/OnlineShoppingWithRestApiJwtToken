package uz.in.onlineshoppingwithrestapijwttoken.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "categories")
public class Category extends BaseEntity{

    @Column(nullable = false)
    private String name;

}
