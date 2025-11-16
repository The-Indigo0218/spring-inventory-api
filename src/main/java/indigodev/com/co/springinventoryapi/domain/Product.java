package indigodev.com.co.springinventoryapi.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
@EqualsAndHashCode(callSuper = true,  onlyExplicitlyIncluded = true)
@SuperBuilder(toBuilder = true)
public class Product extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private double stock;
    @Builder.Default
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Movement> movements = new ArrayList<>();

    public void addMovement(Movement movement) {
        this.movements.add(movement);
        movement.setProduct(this);
    }

    public void removeMovement(Movement movement) {
        this.movements.remove(movement);
        movement.setProduct(null);
    }
}
