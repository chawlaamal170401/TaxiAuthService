package org.amal.taxiauthservice.dto;

import lombok.*;
import org.amal.taxiauthservice.models.Passenger;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO {
  private Long id;
  private String name;
  private String email;
  private String password;
  private String phoneNumber;
  private Date createdAt;

  public static PassengerDTO from(Passenger p){
    PassengerDTO result = PassengerDTO.builder()
                                      .id(p.getId())
                                      .password(p.getPassword())
                                      .phoneNumber(p.getPhoneNumber())
                                      .email(p.getEmail())
                                      .createdAt(p.getCreatedAt())
                                      .build();

    return result;
  }

}
