package com.example.chatdemo.chatLog.UserFiles;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class jwt {

    // A secret "password" only the server knows.
    // This is used to lock (sign) and unlock (verify) tokens.
    private static final String SECRET = "myverylongsecretkeymyverylongsecretkey"; // needs to be long
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    // -------------------------------
    // 1. MAKE A TOKEN (like printing a hall pass)
    // -------------------------------
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder() // start building the token
                .setSubject(userDetails.getUsername()) // put the student's name (username) inside the token
                .setIssuedAt(new Date()) // time when the token was made
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                // expiration date → this pass works for 10 hours only
                .signWith(SECRET_KEY) // lock it with our secret key (like stamping it so no one can fake it)
                .compact(); // squish everything into one string (the JWT you give to the user)
    }

    // -------------------------------
    // 2. READ A TOKEN (check what’s inside the hall pass)
    // -------------------------------
    public String extractUsername(String token) {
        Claims claims = Jwts.parser() // use the parser (like a teacher reading the hall pass)
                .setSigningKey(SECRET_KEY) // unlock the token with the secret key
                .parseClaimsJws(token) // read and check the token
                .getBody(); // grab the inside stuff (claims = the info inside the token)

        return claims.getSubject(); // get the username (the "name on the hall pass")
    }

    // -------------------------------
    // 3. CHECK IF TOKEN IS STILL GOOD
    // -------------------------------
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration(); // when does this pass expire?

        return expiration.before(new Date()); // if it's already past that time → it's expired
    }

    // -------------------------------
    // 4. VALIDATE TOKEN
    // -------------------------------
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token); // get the name written on the hall pass
        return username.equals(userDetails.getUsername()) // name on pass == actual student name?
                && !isTokenExpired(token); // and the pass hasn't expired yet?
        // If both true → the token is valid
    }
}
