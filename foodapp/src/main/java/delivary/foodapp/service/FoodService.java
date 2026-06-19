package delivary.foodapp.service;

import delivary.foodapp.io.FoodRequest;
import delivary.foodapp.io.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodService {
    String uploadFile(MultipartFile file);
    FoodResponse addFood(FoodRequest request , MultipartFile file);
    FoodResponse readFood(String id);
    List<FoodResponse> readFoods();
    boolean deleteFile(String filename );
    void deleteFood(String id);
}
