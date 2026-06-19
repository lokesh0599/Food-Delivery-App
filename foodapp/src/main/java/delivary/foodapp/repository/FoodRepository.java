package delivary.foodapp.repository;

import delivary.foodapp.Entity.FoodEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRepository extends MongoRepository<FoodEntity , String> {
}
