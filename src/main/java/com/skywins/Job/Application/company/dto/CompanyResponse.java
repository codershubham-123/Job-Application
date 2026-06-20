package com.skywins.Job.Application.company.dto;

import com.skywins.Job.Application.company.Company;
import com.skywins.Job.Application.job.Job;
import com.skywins.Job.Application.review.Review;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyResponse {
  private Long id;
  private String name;
  private String description;
  private String logoUrl;
  private String industry;
  private String location;
  private String initial;
  private double rating;
  private int reviewCount;
  private int openJobCount;

  public static CompanyResponse from(Company company) {
    List<Review> reviews =
        company.getReviews() == null ? Collections.emptyList() : company.getReviews();
    List<Job> jobs = company.getJobs() == null ? Collections.emptyList() : company.getJobs();
    double averageRating = reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);

    return new CompanyResponse(
        company.getId(),
        company.getName(),
        company.getDescription(),
        company.getLogoUrl(),
        company.getIndustry(),
        company.getLocation(),
        buildInitial(company.getName()),
        Math.round(averageRating * 10.0) / 10.0,
        reviews.size(),
        jobs.size());
  }

  private static String buildInitial(String name) {
    if (name == null || name.isBlank()) {
      return null;
    }
    return name.trim().substring(0, 1).toUpperCase();
  }
}
