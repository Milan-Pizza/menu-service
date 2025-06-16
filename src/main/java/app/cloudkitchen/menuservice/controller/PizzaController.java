package app.cloudkitchen.menuservice.controller;

import app.cloudkitchen.menuservice.dto.PizzaDTO;
import app.cloudkitchen.menuservice.service.PizzaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    @PostMapping
    public ResponseEntity<PizzaDTO> createPizza(@Valid @RequestBody PizzaDTO pizzaDTO) {
        PizzaDTO createdPizza = pizzaService.createPizza(pizzaDTO);
        return ResponseEntity.ok(createdPizza);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaDTO> getPizzaById(@PathVariable String id) {
        return pizzaService.getPizzaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PizzaDTO>> getAllPizzas() {
        List<PizzaDTO> pizzas = pizzaService.getAllPizzas();
        return ResponseEntity.ok(pizzas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PizzaDTO> updatePizza(@PathVariable String id, @Valid @RequestBody PizzaDTO pizzaDTO) {
        PizzaDTO updatedPizza = pizzaService.updatePizza(id, pizzaDTO);
        return ResponseEntity.ok(updatedPizza);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePizza(@PathVariable String id) {
        pizzaService.deletePizza(id);
        return ResponseEntity.noContent().build();
    }
}
