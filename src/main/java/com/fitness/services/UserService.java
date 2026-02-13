package com.fitness.services;

import com.fitness.dto.user.LoginRequest;
import com.fitness.dto.user.RegisterRequest;
import com.fitness.dto.user.UserResponse;
import com.fitness.model.User;
import com.fitness.model.UserRole;
import com.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserResponse register(RegisterRequest request) {

        UserRole role = request.getRole() != null ? request.getRole()
                : UserRole.USER;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dob = LocalDate.parse(request.getDOB(), formatter);
        Date dobDate = Date.valueOf(dob);

        User user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .DOB(dobDate)
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();


//        User user = new User(
//            null,
//                request.getFirstName(),
//                request.getLastName(),
//                request.getDOB(),
//                request.getEmail(),
//                request.getPassword(),
////                Instant.parse("2026-1-23T14:49:41.208Z")
////                        .atZone(ZoneOffset.UTC)
////                        .toLocalDate(),
////                Instant.parse("2026-1-23T14:49:41.208Z")
////                        .atZone(ZoneOffset.UTC)
////                        .toLocalDate(),
//                    List.of(),
//                    List.of()
//        );

        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    public UserResponse mapToResponse(User savedUser) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setPassword(savedUser.getPassword());
        userResponse.setUpdatedAt(savedUser.getUpdatedAt());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        userResponse.setUpdatedAt(savedUser.getUpdatedAt());
        return userResponse;
    }

    public User authenticated(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(
                loginRequest.getEmail()
        );
        if (user == null)
            throw new RuntimeException("Invalid Credentials" +
                    "Username/email is invalid or not registered");

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Credentials" +
                    "User password is incorrect");
        }

        return user;
    }

//    public User register(RegisterRequest request) {
//        User user = new User();
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setEmail(request.getEmail());
//        user.setPassword(request.getPassword()); // Remember to encode this!
//        user.setDOB(request.getDOB());
//
//        // createdAt and updatedAt will be set automatically by @CreationTimestamp and @UpdateTimestamp
//        // activities and recommendations will be initialized as empty lists
//
//        return userRepository.save(user);
//    }

}
