package com.skywins.Job.Application.job;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.skywins.Job.Application.company.Company;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "job")
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString(exclude = "company")
@EqualsAndHashCode(exclude = "company")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Job {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "minSalary")
  private String minSalary;

  @Column(name = "maxSalary")
  private String maxSalary;

  @Column(name = "location")
  private String location;

  @Column(name = "job_type")
  private String jobType;

  @Column(name = "employment_type")
  private String employmentType;

  @Column(name = "min_experience")
  private Integer minExperience;

  @Column(name = "max_experience")
  private Integer maxExperience;

  @ElementCollection
  @CollectionTable(name = "job_skills", joinColumns = @JoinColumn(name = "job_id"))
  @Column(name = "skill")
  private List<String> skills;

  @Column(name = "posted_at")
  private LocalDateTime postedAt;

  @PrePersist
  public void prePersist() {
    if (postedAt == null) {
      postedAt = LocalDateTime.now();
    }
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company")
  @JsonIgnoreProperties("jobs")
  private Company company;

}
