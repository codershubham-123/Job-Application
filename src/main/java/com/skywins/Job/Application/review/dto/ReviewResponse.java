package com.skywins.Job.Application.review.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skywins.Job.Application.company.dto.CompanySummaryResponse;
import com.skywins.Job.Application.review.Review;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewResponse {
  private Long id;
  private String title;
  private String description;
  private double rating;
  private String pros;
  private String cons;
  private String reviewerRole;
  private String employmentStatus;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  private LocalDateTime createdAt;

  private CompanySummaryResponse company;

  public static ReviewResponse from(Review review) {
    return new ReviewResponse(
        review.getId(),
        review.getTitle(),
        review.getDescription(),
        review.getRating(),
        review.getPros(),
        review.getCons(),
        review.getReviewerRole(),
        review.getEmploymentStatus(),
        review.getCreatedAt(),
        CompanySummaryResponse.from(review.getCompany()));
  }
}
