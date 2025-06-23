package app.cloudkitchen.menuservice.repository;

import app.cloudkitchen.menuservice.entity.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PizzaRepository extends MongoRepository<Pizza, String> {
    Page<Pizza> findByCategoryIgnoreCase(String category, Pageable pageable);
    Page<Pizza> findByIsVegetarian(Boolean isVegetarian, Pageable pageable);
    Page<Pizza> findByIsVegan(Boolean isVegan, Pageable pageable);
    Page<Pizza> findByIsActive(Boolean isActive, Pageable pageable);

    // Keep existing non-paginated methods if needed
    List<Pizza> findByCategoryIgnoreCase(String category);
    List<Pizza> findByIsVegetarian(Boolean isVegetarian);
    List<Pizza> findByIsVegan(Boolean isVegan);
    List<Pizza> findByIsActive(Boolean isActive);
}