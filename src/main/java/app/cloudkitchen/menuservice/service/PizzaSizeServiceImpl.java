package app.cloudkitchen.menuservice.service;

import app.cloudkitchen.menuservice.dto.PagedResponseDTO;
import app.cloudkitchen.menuservice.dto.PizzaSizeDTO;
import app.cloudkitchen.menuservice.entity.Pizza;
import app.cloudkitchen.menuservice.entity.PizzaSize;
import app.cloudkitchen.menuservice.entity.Size;
import app.cloudkitchen.menuservice.exception.PizzaNotFoundException;
import app.cloudkitchen.menuservice.exception.PizzaSizeNotFoundException;
import app.cloudkitchen.menuservice.repository.PizzaRepository;
import app.cloudkitchen.menuservice.repository.PizzaSizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PizzaSizeServiceImpl implements PizzaSizeService {

    private final PizzaSizeRepository pizzaSizeRepository;
    private final PizzaRepository pizzaRepository;

    @Override
    @Transactional
    public PizzaSizeDTO createPizzaSize(PizzaSizeDTO pizzaSizeDTO) {
        PizzaSize pizzaSize = new PizzaSize();
        mapPizzaSizeDtoToEntity(pizzaSizeDTO, pizzaSize);

        PizzaSize savedSize = pizzaSizeRepository.save(pizzaSize);
        return PizzaSizeDTO.fromEntity(savedSize);
    }

    @Override
    public PizzaSizeDTO getPizzaSizeById(String id) {
        PizzaSize pizzaSize = pizzaSizeRepository.findById(id)
                .orElseThrow(() -> new PizzaSizeNotFoundException("Pizza size not found with id: " + id));
        return PizzaSizeDTO.fromEntity(pizzaSize);
    }

    @Override
    public PagedResponseDTO<PizzaSizeDTO> getPizzaSizesByPizzaId(String pizzaId, Pageable pageable) {
        Page<PizzaSize> sizePage = pizzaSizeRepository.findByPizzaId(pizzaId, pageable);
        return buildPagedResponse(sizePage);
    }

    @Override
    @Transactional
    public PizzaSizeDTO updatePizzaSize(String id, PizzaSizeDTO pizzaSizeDTO) {
        PizzaSize existingSize = pizzaSizeRepository.findById(id)
                .orElseThrow(() -> new PizzaSizeNotFoundException("Pizza size not found with id: " + id));

        mapPizzaSizeDtoToEntity(pizzaSizeDTO, existingSize);

        PizzaSize updatedSize = pizzaSizeRepository.save(existingSize);
        return PizzaSizeDTO.fromEntity(updatedSize);
    }

    @Override
    @Transactional
    public void deletePizzaSize(String id) {
        PizzaSize size = pizzaSizeRepository.findById(id)
                .orElseThrow(() -> new PizzaSizeNotFoundException("Pizza size not found with id: " + id));
        pizzaSizeRepository.delete(size);
    }

    @Override
    @Transactional
    public void deleteAllSizesForPizza(String pizzaId) {
        pizzaSizeRepository.deleteByPizzaId(pizzaId);
    }

    @Override
    public PagedResponseDTO<PizzaSizeDTO> getAvailablePizzaSizes(Pageable pageable) {
        Page<PizzaSize> sizePage = pizzaSizeRepository.findByIsAvailable(true, pageable);
        return buildPagedResponse(sizePage);
    }

    private PagedResponseDTO<PizzaSizeDTO> buildPagedResponse(Page<PizzaSize> sizePage) {
        List<PizzaSizeDTO> content = sizePage.getContent()
                .stream()
                .map(PizzaSizeDTO::fromEntity)
                .collect(Collectors.toList());

        return PagedResponseDTO.of(
                content,
                sizePage.getNumber(),
                sizePage.getSize(),
                sizePage.getTotalElements(),
                sizePage.getTotalPages(),
                sizePage.isLast()
        );
    }

    // Non-paginated versions (keep if needed)
    @Override
    public List<PizzaSizeDTO> getPizzaSizesByPizzaId(String pizzaId) {
        return pizzaSizeRepository.findByPizzaId(pizzaId).stream()
                .map(PizzaSizeDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PizzaSizeDTO> getAvailablePizzaSizes() {
        return pizzaSizeRepository.findByIsAvailable(true).stream()
                .map(PizzaSizeDTO::fromEntity)
                .collect(Collectors.toList());
    }

    private void mapPizzaSizeDtoToEntity(PizzaSizeDTO pizzaSizeDTO, PizzaSize pizzaSize) {
        if (pizzaSizeDTO.pizzaId() != null) {
            Pizza pizza = pizzaRepository.findById(pizzaSizeDTO.pizzaId())
                    .orElseThrow(() -> new PizzaNotFoundException("Pizza not found with id: " + pizzaSizeDTO.pizzaId()));
            pizzaSize.setPizza(pizza);
        }

        pizzaSize.setSize(Size.valueOf(pizzaSizeDTO.size()));
        pizzaSize.setPriceModifier(pizzaSizeDTO.priceModifier());
        pizzaSize.setIsAvailable(pizzaSizeDTO.isAvailable());
    }
}