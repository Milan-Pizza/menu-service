package app.cloudkitchen.menuservice.repository;

import app.cloudkitchen.menuservice.entity.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PizzaRepository extends MongoRepository<Pizza, String> {
    List<Pizza> findByIsActiveTrue();
}
