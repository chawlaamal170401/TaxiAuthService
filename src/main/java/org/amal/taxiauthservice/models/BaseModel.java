package org.amal.taxiauthservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel {

  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @Column(nullable = false)
  @Temporal(value = TemporalType.TIMESTAMP)
  @CreatedDate
  protected Date createdAt;

  @Column(nullable = false)
  @Temporal(value = TemporalType.TIMESTAMP)
  @LastModifiedDate
  protected Date updatedAt;


}
