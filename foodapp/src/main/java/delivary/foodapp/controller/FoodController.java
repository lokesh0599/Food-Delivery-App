package delivary.foodapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import delivary.foodapp.io.FoodRequest;
import delivary.foodapp.io.FoodResponse;
import delivary.foodapp.service.FoodService;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.server.ResponseStatusException;



import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FoodController {
    private final FoodService foodService;


    @PostMapping
    public FoodResponse addFood(@RequestPart("food") String foodString ,
                                @RequestPart("file")MultipartFile file){
        ObjectMapper objectMapper = new ObjectMapper();
        FoodRequest request = null;
        try{
            request = objectMapper.readValue(foodString, FoodRequest.class);
        }catch (JsonProcessingException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"Invalid JSON formate");
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Server Error reading string");
        }
        FoodResponse response = foodService.addFood(request, file);
            return response;
    }
    @GetMapping
    public List<FoodResponse> readFoods(){
        return foodService.readFoods();
    }
    @GetMapping("/{id}")
    public FoodResponse readFood(@PathVariable  String id){
        return foodService.readFood(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFood(@PathVariable String id){
        foodService.deleteFood(id);
    }
}
