package org.amal.taxiauthservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.amal.taxiauthservice.models.BaseModel;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseModel {

  @Enumerated(value = EnumType.STRING)
  private BookingStatus bookingStatus;

  @Temporal(value = TemporalType.TIMESTAMP)
  private Date startTime;

  @Temporal(value = TemporalType.TIMESTAMP)
  private Date endTime;

  private Long totalDistance;

  @ManyToOne(fetch = FetchType.LAZY)
  private Driver driver;

  @ManyToOne(fetch = FetchType.LAZY)
  private Passenger passenger;

}
