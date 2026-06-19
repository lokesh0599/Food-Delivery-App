package delivary.foodapp.service;

import delivary.foodapp.Entity.FoodEntity;
import delivary.foodapp.io.FoodRequest;
import delivary.foodapp.io.FoodResponse;
import delivary.foodapp.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FoodServiceImp implements FoodService{
@Autowired
private  S3Client s3Client;
@Autowired
private  FoodRepository foodRepository;

@Value("${aws.s3.bucketName}")
private String bucketName;
    @Override
    public String uploadFile(MultipartFile file) {
        String fileNameExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String key = UUID.randomUUID().toString() + "." + fileNameExtension;
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName).acl("public-read")
                    .key(key)
                    .contentType(file.getContentType())
                    .build();
            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
            if(response.sdkHttpResponse().isSuccessful()){
                return "https://"+bucketName+".s3.amazonaws.com/"+key;
            }else{
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "file upload failed");
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while uploading the file");
        }
    }
    @Override
    public FoodResponse addFood(FoodRequest request , MultipartFile file){
        FoodEntity newFoodEntity = converFoodEntity(request);
        String imageUrl =  uploadFile(file);
        newFoodEntity.setImageUrl(imageUrl);
       newFoodEntity  = foodRepository.save(newFoodEntity);
       return convertToResponse(newFoodEntity);
    }

    public List<FoodResponse> readFoods(){
        List<FoodEntity> databaseEntity = foodRepository.findAll();
        List<FoodResponse> collect = databaseEntity.stream().map(object ->
                convertToResponse(object)).toList();
        return collect;
    }
    private FoodEntity converFoodEntity(FoodRequest request){
       return FoodEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice()).build();
    }
    private FoodResponse convertToResponse(FoodEntity entity){
      return    FoodResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .imageUrl(entity.getImageUrl())
                .build();
    }

    public FoodResponse readFood(String id ){
       FoodEntity existingFood = foodRepository.findById(id).orElseThrow(() -> new RuntimeException("Food id not found :" + id));
       return  convertToResponse(existingFood);
    }
    public boolean deleteFile(String filename){
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
        return true;
    }
    public void deleteFood(String id){
       FoodResponse response = readFood(id);
       String imageUrl =response.getImageUrl();
       String filename = imageUrl.substring(imageUrl.lastIndexOf("/")+1);
       boolean isFileDeleted = deleteFile(filename);
        if(isFileDeleted){
            foodRepository.deleteById(response.getId());
        }
    }
    }





