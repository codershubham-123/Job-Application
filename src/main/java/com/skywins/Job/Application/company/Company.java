package com.skywins.Job.Application.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.skywins.Job.Application.job.Job;
import com.skywins.Job.Application.review.Review;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "company")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "company_name")
  private String name;

  @Column(name = "company_description")
  private String description;

  @JsonIgnore
  @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
  private List<Job> jobs;

  @OneToMany(mappedBy = "company")
  private List<Review> reviews;

  public Company() {}

  public List<Review> getReviews() {
    return reviews;
  }
}
