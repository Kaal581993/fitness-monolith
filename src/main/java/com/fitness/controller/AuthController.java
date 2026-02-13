package com.fitness.controller;


import com.fitness.dto.user.LoginResponse;
import com.fitness.dto.user.LoginRequest;
import com.fitness.dto.user.RegisterRequest;
import com.fitness.dto.user.UserResponse;
import com.fitness.model.User;
import com.fitness.security.JwtUtils;
import com.fitness.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth/users")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    


    private final JwtUtils jwtUtils;
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(userService.register(registerRequest));
    }

//    @GetMapping("/getUser")
//    public ResponseEntity<List<UserResponse>> getUsers(
//            @RequestHeader(value = "X-User-ID") String userId
//
//    ){
//        return ResponseEntity.ok(userRepository.getById(userId));
//    }
//    @PostMapping("/login")
//    public User login(@RequestBody User user){
//        return userService.login(user);
//    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {

            User user = userService.authenticated(loginRequest);
//            User user = userRepository.findByEmail(
//                    loginRequest.getEmail()
//            );
//            if (user == null) return  ResponseEntity.status(401).build();
//            if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
//                return ResponseEntity.status(401).build();
//            }

            String token = jwtUtils.generateToken(user.getId(), user.getRole().name());

            return ResponseEntity.ok(new LoginResponse(
                    token, userService.mapToResponse(user)
            ));

        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }

//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String jwtToken = jwtUtils.generateToken(userDetails);
//        return jwtToken;
    }
}
