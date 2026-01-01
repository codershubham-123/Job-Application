package com.skywins.Job.Application.job;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.skywins.Job.Application.company.Company;
import jakarta.persistence.*;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company")
  @JsonIgnoreProperties("jobs")
  private Company company;

}
