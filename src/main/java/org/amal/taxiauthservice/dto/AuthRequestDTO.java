package org.amal.taxiauthservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDTO {
  private String email;
  private String password;
}
