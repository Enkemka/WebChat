package com.example.chatdemo.chatLog.UserFiles;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {

    private final AuthenticationManager authManager; // checks username + password

    public AuthController(AuthenticationManager authManager, uds userDetailsService, jwt jwtUtil) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    private final uds userDetailsService;
    private final jwt jwtUtil;



    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto request) throws Exception {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid username or password");
        }

        // If login is OK → generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(request);
         // give the "hall pass"
    }
}
