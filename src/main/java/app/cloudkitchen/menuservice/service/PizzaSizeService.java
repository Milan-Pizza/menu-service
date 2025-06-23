package app.cloudkitchen.menuservice.service;

import app.cloudkitchen.menuservice.dto.PagedResponseDTO;
import app.cloudkitchen.menuservice.dto.PizzaSizeDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PizzaSizeService {
    PizzaSizeDTO createPizzaSize(PizzaSizeDTO pizzaSizeDTO);
    PizzaSizeDTO getPizzaSizeById(String id);
    PagedResponseDTO<PizzaSizeDTO> getPizzaSizesByPizzaId(String pizzaId, Pageable pageable);
    PizzaSizeDTO updatePizzaSize(String id, PizzaSizeDTO pizzaSizeDTO);
    void deletePizzaSize(String id);
    void deleteAllSizesForPizza(String pizzaId);

    // Paginated methods
    PagedResponseDTO<PizzaSizeDTO> getAvailablePizzaSizes(Pageable pageable);

    // Non-paginated methods (keep if needed)
    List<PizzaSizeDTO> getPizzaSizesByPizzaId(String pizzaId);
    List<PizzaSizeDTO> getAvailablePizzaSizes();
}