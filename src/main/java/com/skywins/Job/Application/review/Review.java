package com.skywins.Job.Application.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skywins.Job.Application.company.Company;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "review")
@Getter
@Setter
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "rating")
  private double rating;

  @Column(name = "pros")
  private String pros;

  @Column(name = "cons")
  private String cons;

  @Column(name = "reviewer_role")
  private String reviewerRole;

  @Column(name = "employment_status")
  private String employmentStatus;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @PrePersist
  public void prePersist() {
    if (createdAt == null) {
      createdAt = LocalDateTime.now();
    }
  }

  @JsonIgnore @ManyToOne private Company company;

  public Review() {}


}
