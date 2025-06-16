package app.cloudkitchen.menuservice.dto;

import app.cloudkitchen.menuservice.entity.PizzaSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PizzaSizeDTO(
    String id,
    @NotBlank String pizzaId,
    @NotNull String size,
    @NotNull @Positive BigDecimal priceModifier,
    @NotNull Boolean isAvailable
) {
    public static PizzaSizeDTO fromEntity(PizzaSize pizzaSize) {
        return new PizzaSizeDTO(
            pizzaSize.getId(),
            pizzaSize.getPizza() != null ? pizzaSize.getPizza().getId() : null,
            pizzaSize.getSize() != null ? pizzaSize.getSize().name() : null,
            pizzaSize.getPriceModifier(),
            pizzaSize.getIsAvailable()
        );
    }
}