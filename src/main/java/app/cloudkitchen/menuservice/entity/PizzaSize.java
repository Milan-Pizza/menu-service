package app.cloudkitchen.menuservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Document(collation = "pizza_sizes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PizzaSize extends BaseEntity {

    @Id
    private String id;

    @DBRef
    private Pizza pizza;

    @Field("size")
    private Size size;

    @Field("price_modifier")
    private BigDecimal priceModifier;

    @Field("is_available")
    private Boolean isAvailable = true;

}
