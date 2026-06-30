package delivary.foodapp.service;

import delivary.foodapp.io.UserRequest;
import delivary.foodapp.io.UserResponse;

public interface UserService {
   UserResponse registerUser(UserRequest request);
}
