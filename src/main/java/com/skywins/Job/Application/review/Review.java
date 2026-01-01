package com.skywins.Job.Application.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skywins.Job.Application.company.Company;
import jakarta.persistence.*;
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

  @JsonIgnore @ManyToOne private Company company;

  public Review() {}


}
