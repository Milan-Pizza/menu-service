package app.cloudkitchen.menuservice.repository;

import app.cloudkitchen.menuservice.entity.PizzaSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PizzaSizeRepository extends MongoRepository<PizzaSize, String> {
    Page<PizzaSize> findByPizzaId(String pizzaId, Pageable pageable);
    Page<PizzaSize> findByIsAvailable(Boolean isAvailable, Pageable pageable);

    // Keep existing non-paginated methods if needed
    List<PizzaSize> findByPizzaId(String pizzaId);
    List<PizzaSize> findByIsAvailable(Boolean isAvailable);

    void deleteByPizzaId(String id);
}