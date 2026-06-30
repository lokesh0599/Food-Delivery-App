package delivary.foodapp.controller;

import delivary.foodapp.Util.JwtUtil;
import delivary.foodapp.io.AuthenticationRequest;
import delivary.foodapp.io.AuthenticationResponse;
import delivary.foodapp.service.AppUserDetailsService;
import delivary.foodapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthenticationManager authenticationManager;
    private final AppUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request){
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
       final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
       final String jwtToken = jwtUtil.generateToken(userDetails);
       return new AuthenticationResponse(request.getEmail(), jwtToken);
    }
}
