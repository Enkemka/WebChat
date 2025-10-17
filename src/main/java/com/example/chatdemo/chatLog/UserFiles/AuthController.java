package com.example.chatdemo.chatLog.UserFiles;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {

    private final AuthenticationManager authManager; // checks username + password
    private final uds userDetailsService;
    private final jwt jwtUtil;

    @Autowired
    private UserService userService;

    public AuthController(AuthenticationManager authManager, uds userDetailsService, jwt jwtUtil) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserDto request) throws Exception {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid username or password");
        }

        // If login is OK → generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
       String token = jwtUtil.generateToken(userDetails);

        UserResponse userResponse = new UserResponse(token, request.getUsername());

        return ResponseEntity.ok(userResponse);
         // give the "hall pass"
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterDto> RegisterUser(@RequestBody UserRegisterDto userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.ok(userDto);

    }

}
