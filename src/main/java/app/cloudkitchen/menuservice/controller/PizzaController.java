package app.cloudkitchen.menuservice.controller;

import app.cloudkitchen.menuservice.dto.PagedResponse;
import app.cloudkitchen.menuservice.dto.PizzaDTO;
import app.cloudkitchen.menuservice.service.PizzaService;
import app.cloudkitchen.menuservice.util.PaginationUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
@Validated
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
    public ResponseEntity<PagedResponse<PizzaDTO>> getAllPizzas(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("desc") ?
            Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Page<PizzaDTO> pizzaPage = pizzaService.getAllPizzas(
            PageRequest.of(page, size, sort));

        return ResponseEntity.ok(PaginationUtil.fromPage(pizzaPage));
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
