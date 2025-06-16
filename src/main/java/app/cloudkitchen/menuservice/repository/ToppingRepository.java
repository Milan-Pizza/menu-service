package app.cloudkitchen.menuservice.repository;

import app.cloudkitchen.menuservice.entity.Topping;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToppingRepository extends MongoRepository<Topping, String> {

}
