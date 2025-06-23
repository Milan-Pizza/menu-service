package app.cloudkitchen.menuservice.service;

import app.cloudkitchen.menuservice.dto.PizzaDTO;
import app.cloudkitchen.menuservice.entity.Pizza;
import app.cloudkitchen.menuservice.entity.PizzaSize;
import app.cloudkitchen.menuservice.repository.PizzaRepository;
import app.cloudkitchen.menuservice.repository.PizzaSizeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaSizeRepository pizzaSizeRepository;

    public PizzaServiceImpl(PizzaRepository pizzaRepository, PizzaSizeRepository pizzaSizeRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaSizeRepository = pizzaSizeRepository;
    }

    @Override
    public PizzaDTO createPizza(PizzaDTO pizzaDTO) {
        Pizza pizza = toEntity(pizzaDTO);
        Pizza saved = pizzaRepository.save(pizza);
        return PizzaDTO.fromEntity(saved);
    }

    @Override
    public Optional<PizzaDTO> getPizzaById(String id) {
        return pizzaRepository.findById(id).map(PizzaDTO::fromEntity);
    }

    @Override
    public Page<PizzaDTO> getAllPizzas(Pageable pageable) {
        return pizzaRepository.findAll(pageable)
                .map(PizzaDTO::fromEntity);
    }

    @Override
    public PizzaDTO updatePizza(String id, PizzaDTO pizzaDTO) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pizza not found"));
        // Update fields
        pizza.setName(pizzaDTO.name());
        pizza.setDescription(pizzaDTO.description());
        pizza.setBasePrice(pizzaDTO.basePrice());
        pizza.setCategory(pizzaDTO.category());
        pizza.setIsVegetarian(pizzaDTO.isVegetarian());
        pizza.setIsVegan(pizzaDTO.isVegan());
        pizza.setIsActive(pizzaDTO.isActive());
        pizza.setImageUrl(pizzaDTO.imageUrl());
        // Update available sizes
        List<PizzaSize> sizes = pizzaSizeRepository.findAllById(pizzaDTO.availableSizeIds());
        pizza.setAvailableSizes(sizes);
        Pizza updated = pizzaRepository.save(pizza);
        return PizzaDTO.fromEntity(updated);
    }

    @Override
    public void deletePizza(String id) {
        pizzaRepository.deleteById(id);
    }

    // Helper method to convert DTO to entity
    private Pizza toEntity(PizzaDTO dto) {
        Pizza pizza = new Pizza();
        pizza.setId(dto.id());
        pizza.setName(dto.name());
        pizza.setDescription(dto.description());
        pizza.setBasePrice(dto.basePrice());
        pizza.setCategory(dto.category());
        pizza.setIsVegetarian(dto.isVegetarian());
        pizza.setIsVegan(dto.isVegan());
        pizza.setIsActive(dto.isActive());
        pizza.setImageUrl(dto.imageUrl());
        List<PizzaSize> sizes = pizzaSizeRepository.findAllById(dto.availableSizeIds());
        pizza.setAvailableSizes(sizes);
        return pizza;
    }
}