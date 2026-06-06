package com.skywins.Job.Application.job.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skywins.Job.Application.company.dto.CompanySummaryResponse;
import com.skywins.Job.Application.job.Job;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobResponse {
  private Long id;
  private String title;
  private String description;
  private String minSalary;
  private String maxSalary;
  private String location;
  private String jobType;
  private String employmentType;
  private ExperienceResponse experience;
  private List<String> skills;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  private LocalDateTime postedAt;

  private boolean isNew;
  private SalaryResponse salary;
  private CompanySummaryResponse company;

  public static JobResponse from(Job job) {
    return new JobResponse(
        job.getId(),
        job.getTitle(),
        job.getDescription(),
        job.getMinSalary(),
        job.getMaxSalary(),
        job.getLocation(),
        job.getJobType(),
        job.getEmploymentType(),
        ExperienceResponse.from(job.getMinExperience(), job.getMaxExperience()),
        job.getSkills() == null ? Collections.emptyList() : job.getSkills(),
        job.getPostedAt(),
        isNew(job.getPostedAt()),
        SalaryResponse.from(job.getMinSalary(), job.getMaxSalary()),
        CompanySummaryResponse.from(job.getCompany()));
  }

  private static boolean isNew(LocalDateTime postedAt) {
    if (postedAt == null) {
      return false;
    }
    long ageInDays = ChronoUnit.DAYS.between(postedAt, LocalDateTime.now());
    return ageInDays >= 0 && ageInDays <= 7;
  }
}
