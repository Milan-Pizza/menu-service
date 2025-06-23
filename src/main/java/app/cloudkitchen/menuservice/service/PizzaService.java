package app.cloudkitchen.menuservice.service;

import app.cloudkitchen.menuservice.dto.PizzaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PizzaService {
    PizzaDTO createPizza(PizzaDTO pizzaDTO);
    Optional<PizzaDTO> getPizzaById(String id);
    Page<PizzaDTO> getAllPizzas(Pageable pageable);
    PizzaDTO updatePizza(String id, PizzaDTO pizzaDTO);
    void deletePizza(String id);
}