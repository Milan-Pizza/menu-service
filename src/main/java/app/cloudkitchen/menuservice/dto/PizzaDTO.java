package app.cloudkitchen.menuservice.dto;

import app.cloudkitchen.menuservice.entity.Pizza;
import app.cloudkitchen.menuservice.entity.PizzaSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record PizzaDTO(
        String id,
        @NotBlank String name,
        @NotBlank String description,
        @NotNull @Positive BigDecimal basePrice,
        @NotBlank String category,
        @NotNull Boolean isVegetarian,
        @NotNull Boolean isVegan,
        @NotNull Boolean isActive,
        String imageUrl,
        @NotNull List<@NotBlank String> availableSizeIds
) {
    public static PizzaDTO fromEntity(Pizza pizza) {
        return new PizzaDTO(
                pizza.getId(),
                pizza.getName(),
                pizza.getDescription(),
                pizza.getBasePrice(),
                pizza.getCategory(),
                pizza.getIsVegetarian(),
                pizza.getIsVegan(),
                pizza.getIsActive(),
                pizza.getImageUrl(),
                pizza.getAvailableSizes().stream()
                        .map(PizzaSize::getId)
                        .toList()
        );
    }
}

