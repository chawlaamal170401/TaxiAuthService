package org.amal.taxiauthservice.service;

import org.amal.taxiauthservice.dto.PassengerDTO;
import org.amal.taxiauthservice.dto.PassengerSignUpRequestDTO;
import org.amal.taxiauthservice.models.Passenger;
import org.amal.taxiauthservice.repositories.PassengerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final PassengerRepository passengerRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public AuthService(PassengerRepository passengerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.passengerRepository = passengerRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  public PassengerDTO signUpPassenger(PassengerSignUpRequestDTO passengerSignUpRequestDTO){
    Passenger passenger = Passenger.builder()
                                  .password(bCryptPasswordEncoder.encode(passengerSignUpRequestDTO.getPassword()))
                                  .name(passengerSignUpRequestDTO.getName())
                                  .email(passengerSignUpRequestDTO.getEmail())
                                  .phoneNumber(passengerSignUpRequestDTO.getPhoneNumber())
                                  .build();

    passengerRepository.save(passenger);
    return PassengerDTO.from(passenger);
  }

}
