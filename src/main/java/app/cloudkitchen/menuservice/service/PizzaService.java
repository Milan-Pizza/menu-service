package app.cloudkitchen.menuservice.service;

import app.cloudkitchen.menuservice.dto.PizzaDTO;
import java.util.List;
import java.util.Optional;

public interface PizzaService {
    PizzaDTO createPizza(PizzaDTO pizzaDTO);
    Optional<PizzaDTO> getPizzaById(String id);
    List<PizzaDTO> getAllPizzas();
    PizzaDTO updatePizza(String id, PizzaDTO pizzaDTO);
    void deletePizza(String id);
}