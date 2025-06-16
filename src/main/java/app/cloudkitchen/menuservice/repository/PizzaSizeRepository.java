package app.cloudkitchen.menuservice.repository;

import app.cloudkitchen.menuservice.entity.PizzaSize;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaSizeRepository extends MongoRepository<PizzaSize, String> {

}
