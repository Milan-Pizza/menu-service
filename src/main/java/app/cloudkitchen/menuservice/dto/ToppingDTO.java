package app.cloudkitchen.menuservice.dto;

import app.cloudkitchen.menuservice.entity.Topping;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public record ToppingDTO(
    String id,
    @NotBlank String name,
    @NotNull @Positive BigDecimal price,
    @NotNull String category,
    @NotNull Boolean isAvailable,
    @NotNull Set<String> allergens
) {
    public static ToppingDTO fromEntity(Topping topping) {
        return new ToppingDTO(
            topping.getId(),
            topping.getName(),
            topping.getPrice(),
            topping.getCategory() != null ? topping.getCategory().name() : null,
            topping.getIsAvailable(),
            topping.getAllergens() != null
                ? topping.getAllergens().stream().map(Enum::name).collect(Collectors.toSet())
                : Set.of()
        );
    }
}