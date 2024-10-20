package org.amal.taxiauthservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" , "bookingList"})
public class Driver extends BaseModel {
  private String name;

  @Column(nullable = false, unique = true)
  private String licenseNumber;

  private String phoneNumber;

  @OneToMany(mappedBy = "driver")
  @Fetch(FetchMode.SUBSELECT)
  private List<Booking> bookingList = new ArrayList<>();

}
