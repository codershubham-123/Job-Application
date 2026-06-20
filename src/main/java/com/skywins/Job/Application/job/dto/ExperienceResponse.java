package com.skywins.Job.Application.job.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExperienceResponse {
  private Integer minYears;
  private Integer maxYears;
  private String label;

  public static ExperienceResponse from(Integer minYears, Integer maxYears) {
    return new ExperienceResponse(minYears, maxYears, buildLabel(minYears, maxYears));
  }

  private static String buildLabel(Integer minYears, Integer maxYears) {
    if (minYears == null && maxYears == null) {
      return null;
    }
    if (minYears != null && maxYears != null) {
      return minYears.equals(maxYears)
          ? minYears + " Years"
          : minYears + " - " + maxYears + " Years";
    }
    if (minYears != null) {
      return minYears + "+ Years";
    }
    return "Up to " + maxYears + " Years";
  }
}
