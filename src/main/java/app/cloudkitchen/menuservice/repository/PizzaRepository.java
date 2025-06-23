package app.cloudkitchen.menuservice.repository;

import app.cloudkitchen.menuservice.entity.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaRepository extends MongoRepository<Pizza, String> {
    Page<Pizza> findByIsActiveTrue(Pageable pageable);
}
