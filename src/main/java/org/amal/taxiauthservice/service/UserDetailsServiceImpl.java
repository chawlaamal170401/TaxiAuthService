package org.amal.taxiauthservice.service;

import org.amal.taxiauthservice.helpers.AuthPassengerDetails;
import org.amal.taxiauthservice.models.Passenger;
import org.amal.taxiauthservice.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private PassengerRepository passengerRepository;


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<Passenger> passenger = passengerRepository.findPassengerByEmail(email);
    if(passenger.isPresent()){
      return new AuthPassengerDetails(passenger.get());
    } else {
      throw new UsernameNotFoundException("Cannot find the Passenger by the given Email");
    }

  }
}
