package app.cloudkitchen.menuservice.service;

import app.cloudkitchen.menuservice.dto.PagedResponseDTO;
import app.cloudkitchen.menuservice.dto.PizzaDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PizzaService {
    PizzaDTO createPizza(PizzaDTO pizzaDTO);
    PizzaDTO getPizzaById(String id);
    PagedResponseDTO<PizzaDTO> getAllPizzas(Pageable pageable);
    PizzaDTO updatePizza(String id, PizzaDTO pizzaDTO);
    void deletePizza(String id);

    // Paginated methods
    PagedResponseDTO<PizzaDTO> getPizzasByCategory(String category, Pageable pageable);
    PagedResponseDTO<PizzaDTO> getVegetarianPizzas(Pageable pageable);
    PagedResponseDTO<PizzaDTO> getVeganPizzas(Pageable pageable);
    PagedResponseDTO<PizzaDTO> getActivePizzas(Pageable pageable);

    // Non-paginated methods (keep if needed)
    List<PizzaDTO> getPizzasByCategory(String category);
    List<PizzaDTO> getVegetarianPizzas();
    List<PizzaDTO> getVeganPizzas();
}