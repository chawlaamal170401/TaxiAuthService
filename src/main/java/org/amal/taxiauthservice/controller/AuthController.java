package org.amal.taxiauthservice.controller;

import org.amal.taxiauthservice.dto.PassengerDTO;
import org.amal.taxiauthservice.dto.PassengerSignUpRequestDTO;
import org.amal.taxiauthservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService){
    this.authService = authService;
  }

  @PostMapping("/signup/passenger")
  public ResponseEntity<PassengerDTO> register(@RequestBody PassengerSignUpRequestDTO passengerSignUpRequestDTO){
    PassengerDTO response = authService.signUpPassenger(passengerSignUpRequestDTO);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
