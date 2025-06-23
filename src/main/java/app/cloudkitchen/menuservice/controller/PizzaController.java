package app.cloudkitchen.menuservice.controller;

import app.cloudkitchen.menuservice.dto.PagedResponseDTO;
import app.cloudkitchen.menuservice.dto.PizzaDTO;
import app.cloudkitchen.menuservice.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    // Pizza endpoints
    @PostMapping
    public ResponseEntity<PizzaDTO> createPizza(@RequestBody PizzaDTO pizzaDTO) {
        return ResponseEntity.ok(pizzaService.createPizza(pizzaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaDTO> getPizzaById(@PathVariable String id) {
        return ResponseEntity.ok(pizzaService.getPizzaById(id));
    }

    @GetMapping
    public ResponseEntity<PagedResponseDTO<PizzaDTO>> getAllPizzas(
            @PageableDefault(size = 20, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(pizzaService.getAllPizzas(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PizzaDTO> updatePizza(@PathVariable String id, @RequestBody PizzaDTO pizzaDTO) {
        return ResponseEntity.ok(pizzaService.updatePizza(id, pizzaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePizza(@PathVariable String id) {
        pizzaService.deletePizza(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<PagedResponseDTO<PizzaDTO>> getPizzasByCategory(
            @PathVariable String category,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(pizzaService.getPizzasByCategory(category, pageable));
    }

    @GetMapping("/vegetarian")
    public ResponseEntity<PagedResponseDTO<PizzaDTO>> getVegetarianPizzas(
            @PageableDefault(size = 15) Pageable pageable) {
        return ResponseEntity.ok(pizzaService.getVegetarianPizzas(pageable));
    }

    @GetMapping("/vegan")
    public ResponseEntity<PagedResponseDTO<PizzaDTO>> getVeganPizzas(
            @PageableDefault(size = 15) Pageable pageable) {
        return ResponseEntity.ok(pizzaService.getVeganPizzas(pageable));
    }

    @GetMapping("/active")
    public ResponseEntity<PagedResponseDTO<PizzaDTO>> getActivePizzas(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(pizzaService.getActivePizzas(pageable));
    }

}