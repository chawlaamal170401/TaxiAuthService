package org.amal.taxiauthservice.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.amal.taxiauthservice.dto.AuthRequestDTO;
import org.amal.taxiauthservice.dto.AuthResponseDTO;
import org.amal.taxiauthservice.dto.PassengerDTO;
import org.amal.taxiauthservice.dto.PassengerSignUpRequestDTO;
import org.amal.taxiauthservice.service.AuthService;
import org.amal.taxiauthservice.service.JWTService;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;
  private final AuthenticationManager authenticationManager;
  private final JWTService jwtService;

  public AuthController(AuthService authService, AuthenticationManager authenticationManager, JWTService jwtService){
    this.authService = authService;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
  }

  @PostMapping("/signup/passenger")
  public ResponseEntity<PassengerDTO> register(@RequestBody PassengerSignUpRequestDTO passengerSignUpRequestDTO){
    PassengerDTO response = authService.signUpPassenger(passengerSignUpRequestDTO);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }


  @PostMapping("/signin/passenger")
  public ResponseEntity<?> signIn(@RequestBody AuthRequestDTO authRequestDTO, HttpServletResponse httpServletResponse){
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));
    if(authentication.isAuthenticated()){
      String jwtToken = jwtService.createToken(authRequestDTO.getEmail());
      ResponseCookie responseCookie = ResponseCookie.from("jwtToken", jwtToken)
                                                    .httpOnly(true)
                                                    .secure(false)
                                                    .path("/")
                                                    .maxAge(7*24*3600)
                                                    .build();
      httpServletResponse.setHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
      return new ResponseEntity<>(AuthResponseDTO.builder().success(true).build(), HttpStatus.OK);
    } else {
        throw new UsernameNotFoundException("User not found");
    }
  }

  @GetMapping("/validate")
  public ResponseEntity<?> validate(HttpServletRequest request, HttpServletResponse response) {
    for(Cookie cookie: request.getCookies()) {
      System.out.println(cookie.getName() + " " + cookie.getValue());
    }
    return new ResponseEntity<>("Success", HttpStatus.OK);
  }

}
