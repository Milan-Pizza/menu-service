package app.cloudkitchen.menuservice.service;

import app.cloudkitchen.menuservice.dto.PagedResponseDTO;
import app.cloudkitchen.menuservice.dto.PizzaDTO;
import app.cloudkitchen.menuservice.entity.Pizza;
import app.cloudkitchen.menuservice.entity.PizzaSize;
import app.cloudkitchen.menuservice.exception.PizzaNotFoundException;
import app.cloudkitchen.menuservice.repository.PizzaRepository;
import app.cloudkitchen.menuservice.repository.PizzaSizeRepository;
import app.cloudkitchen.menuservice.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaSizeRepository pizzaSizeRepository;

    @Override
    @Transactional
    public PizzaDTO createPizza(PizzaDTO pizzaDTO) {
        Pizza pizza = new Pizza();
        mapPizzaDtoToEntity(pizzaDTO, pizza);

        Pizza savedPizza = pizzaRepository.save(pizza);
        return PizzaDTO.fromEntity(savedPizza);
    }

    @Override
    public PizzaDTO getPizzaById(String id) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new PizzaNotFoundException("Pizza not found with id: " + id));
        return PizzaDTO.fromEntity(pizza);
    }

    @Override
    public PagedResponseDTO<PizzaDTO> getAllPizzas(Pageable pageable) {
        Page<Pizza> pizzaPage = pizzaRepository.findAll(pageable);
        return PaginationUtil.fromPage(pizzaPage, PizzaDTO::fromEntity);
    }

    @Override
    @Transactional
    public PizzaDTO updatePizza(String id, PizzaDTO pizzaDTO) {
        Pizza existingPizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new PizzaNotFoundException("Pizza not found with id: " + id));

        mapPizzaDtoToEntity(pizzaDTO, existingPizza);

        Pizza updatedPizza = pizzaRepository.save(existingPizza);
        return PizzaDTO.fromEntity(updatedPizza);
    }

    @Override
    @Transactional
    public void deletePizza(String id) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new PizzaNotFoundException("Pizza not found with id: " + id));

        // Delete associated sizes first
        pizzaSizeRepository.deleteByPizzaId(id);
        pizzaRepository.delete(pizza);
    }

    @Override
    public PagedResponseDTO<PizzaDTO> getPizzasByCategory(String category, Pageable pageable) {
        Page<Pizza> pizzaPage = pizzaRepository.findByCategoryIgnoreCase(category, pageable);
        return PaginationUtil.fromPage(pizzaPage, PizzaDTO::fromEntity);
    }

    @Override
    public PagedResponseDTO<PizzaDTO> getVegetarianPizzas(Pageable pageable) {
        Page<Pizza> pizzaPage = pizzaRepository.findByIsVegetarian(true, pageable);
        return PaginationUtil.fromPage(pizzaPage, PizzaDTO::fromEntity);
    }

    @Override
    public PagedResponseDTO<PizzaDTO> getVeganPizzas(Pageable pageable) {
        Page<Pizza> pizzaPage = pizzaRepository.findByIsVegan(true, pageable);
        return PaginationUtil.fromPage(pizzaPage, PizzaDTO::fromEntity);
    }

    @Override
    public PagedResponseDTO<PizzaDTO> getActivePizzas(Pageable pageable) {
        Page<Pizza> pizzaPage = pizzaRepository.findByIsActive(true, pageable);
        return PaginationUtil.fromPage(pizzaPage, PizzaDTO::fromEntity);
    }


    // Non-paginated versions (keep if needed)
    @Override
    public List<PizzaDTO> getPizzasByCategory(String category) {
        return pizzaRepository.findByCategoryIgnoreCase(category).stream()
                .map(PizzaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PizzaDTO> getVegetarianPizzas() {
        return pizzaRepository.findByIsVegetarian(true).stream()
                .map(PizzaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PizzaDTO> getVeganPizzas() {
        return pizzaRepository.findByIsVegan(true).stream()
                .map(PizzaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    private void mapPizzaDtoToEntity(PizzaDTO pizzaDTO, Pizza pizza) {
        pizza.setName(pizzaDTO.name());
        pizza.setDescription(pizzaDTO.description());
        pizza.setBasePrice(pizzaDTO.basePrice());
        pizza.setCategory(pizzaDTO.category());
        pizza.setIsVegetarian(pizzaDTO.isVegetarian());
        pizza.setIsVegan(pizzaDTO.isVegan());
        pizza.setIsActive(pizzaDTO.isActive());
        pizza.setImageUrl(pizzaDTO.imageUrl());

        if (pizzaDTO.availableSizeIds() != null) {
            List<PizzaSize> sizes = pizzaSizeRepository.findAllById(pizzaDTO.availableSizeIds());
            pizza.setAvailableSizes(sizes);
        }
    }
}