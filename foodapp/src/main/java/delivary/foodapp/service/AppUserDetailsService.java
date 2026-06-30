package delivary.foodapp.service;

import delivary.foodapp.Entity.UserEntity;
import delivary.foodapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      UserEntity user = userRepository.findByemail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
      return  new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}
