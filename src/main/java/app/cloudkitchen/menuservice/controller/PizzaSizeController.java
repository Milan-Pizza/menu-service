package app.cloudkitchen.menuservice.controller;

import app.cloudkitchen.menuservice.dto.PagedResponseDTO;
import app.cloudkitchen.menuservice.dto.PizzaSizeDTO;
import app.cloudkitchen.menuservice.service.PizzaSizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
public class PizzaSizeController {

    private final PizzaSizeService pizzaSizeService;

    // PizzaSize endpoints
    @PostMapping("/{pizzaId}/sizes")
    public ResponseEntity<PizzaSizeDTO> createPizzaSize(
            @PathVariable String pizzaId,
            @RequestBody PizzaSizeDTO pizzaSizeDTO) {
        PizzaSizeDTO sizeDtoWithPizzaId = new PizzaSizeDTO(
                pizzaSizeDTO.id(),
                pizzaId,
                pizzaSizeDTO.size(),
                pizzaSizeDTO.priceModifier(),
                pizzaSizeDTO.isAvailable()
        );
        return ResponseEntity.ok(pizzaSizeService.createPizzaSize(sizeDtoWithPizzaId));
    }

    @GetMapping("/{pizzaId}/sizes")
    public ResponseEntity<PagedResponseDTO<PizzaSizeDTO>> getPizzaSizes(
            @PathVariable String pizzaId,
            @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(pizzaSizeService.getPizzaSizesByPizzaId(pizzaId, pageable));
    }

    @GetMapping("/sizes/available")
    public ResponseEntity<PagedResponseDTO<PizzaSizeDTO>> getAvailablePizzaSizes(
            @PageableDefault(size = 10, sort = "priceModifier") Pageable pageable) {
        return ResponseEntity.ok(pizzaSizeService.getAvailablePizzaSizes(pageable));
    }

    @GetMapping("/sizes/{id}")
    public ResponseEntity<PizzaSizeDTO> getPizzaSizeById(@PathVariable String id) {
        return ResponseEntity.ok(pizzaSizeService.getPizzaSizeById(id));
    }

    @PutMapping("/sizes/{id}")
    public ResponseEntity<PizzaSizeDTO> updatePizzaSize(
            @PathVariable String id,
            @RequestBody PizzaSizeDTO pizzaSizeDTO) {
        return ResponseEntity.ok(pizzaSizeService.updatePizzaSize(id, pizzaSizeDTO));
    }

    @DeleteMapping("/sizes/{id}")
    public ResponseEntity<Void> deletePizzaSize(@PathVariable String id) {
        pizzaSizeService.deletePizzaSize(id);
        return ResponseEntity.noContent().build();
    }

}
