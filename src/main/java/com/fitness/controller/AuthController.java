package com.fitness.controller;


import com.fitness.dto.activity.ActivityTrackingResponse;
import com.fitness.dto.user.RegisterRequest;
import com.fitness.dto.user.UserResponse;
import com.fitness.repository.UserRepository;
import com.fitness.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth/users")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest){
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
}
