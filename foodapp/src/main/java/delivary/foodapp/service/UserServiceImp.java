package delivary.foodapp.service;

import delivary.foodapp.Entity.UserEntity;
import delivary.foodapp.io.UserRequest;
import delivary.foodapp.io.UserResponse;
import delivary.foodapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

   public UserResponse registerUser(UserRequest request){
        UserEntity newUser = converToEntity(request);
       newUser = userRepository.save(newUser);
       return convertToResponse(newUser);
    }
    private UserEntity converToEntity(UserRequest userRequest){
       return UserEntity.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .name(userRequest.getName()).build();

    }

    private UserResponse convertToResponse(UserEntity register){
       return UserResponse.builder()
                .id(register.getId())
                .name(register.getName())
                .email(register.getEmail()).build();
    }
}
