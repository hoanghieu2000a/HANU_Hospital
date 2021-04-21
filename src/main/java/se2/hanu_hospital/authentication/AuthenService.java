package se2.hanu_hospital.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se2.hanu_hospital.account.model.Role;
import se2.hanu_hospital.account.model.RoleName;
import se2.hanu_hospital.account.model.User;
import se2.hanu_hospital.account.repository.RoleRepository;
import se2.hanu_hospital.account.repository.UserRepository;
import se2.hanu_hospital.authentication.payload.ApiResponse;
import se2.hanu_hospital.authentication.payload.JwtAuthenticationResponse;
import se2.hanu_hospital.authentication.payload.LoginRequest;
import se2.hanu_hospital.authentication.payload.SignUpRequest;
import se2.hanu_hospital.authentication.security.JwtTokenProvider;
import sqa.hanu_minimart.model.Cart;


import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthenService{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String usernameOrEmail = loginRequest.getUsernameOrEmail();
        Optional<User> user = userRepository.findByUsernameOrPhoneNumber(usernameOrEmail, usernameOrEmail );

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, user.get()));
    }

    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            return new ResponseEntity(new ApiResponse(false, "Phone Number Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setAddress(signUpRequest.getAddress());
        user.setStatus("ACTIVATED");

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Optional<Role> userRole = roleRepository.findByName(RoleName.ROLE_CUSTOMER);

        user.setRoles(Collections.singleton(userRole.get()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
