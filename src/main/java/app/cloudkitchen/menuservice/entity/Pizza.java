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
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collation = "pizzas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Pizza extends BaseEntity {

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("base_price")
    private BigDecimal basePrice;

    @Field("category")
    private String category;

    @Field("is_vegetarian")
    private Boolean isVegetarian = false;

    @Field("is_vegan")
    private Boolean isVegan = false;

    @Field("is_active")
    private Boolean isActive = true;

    @Field("image_url")
    private String imageUrl;

    @DBRef(lazy = true)
    private List<PizzaSize> availableSizes = new ArrayList<>();
}
