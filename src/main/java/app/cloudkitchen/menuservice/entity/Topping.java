package app.cloudkitchen.menuservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "toppings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Topping extends BaseEntity {

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("price")
    private BigDecimal price;

    @Field("category")
    private ToppingCategory category;

    @Field("is_available")
    private Boolean isAvailable = true;

    @Field("allergens")
    private Set<Allergen> allergens = new HashSet<>();

}
