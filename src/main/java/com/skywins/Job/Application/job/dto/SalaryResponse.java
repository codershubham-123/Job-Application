package com.skywins.Job.Application.job.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalaryResponse {
  private String currency;
  private Integer min;
  private Integer max;
  private String display;

  public static SalaryResponse from(String minSalary, String maxSalary) {
    Integer min = parseSalary(minSalary);
    Integer max = parseSalary(maxSalary);
    return new SalaryResponse("INR", min, max, buildDisplay(min, max));
  }

  private static Integer parseSalary(String salary) {
    if (salary == null || salary.isBlank()) {
      return null;
    }
    String numericSalary = salary.replaceAll("[^0-9]", "");
    if (numericSalary.isBlank()) {
      return null;
    }
    return Integer.valueOf(numericSalary);
  }

  private static String buildDisplay(Integer min, Integer max) {
    if (min == null && max == null) {
      return null;
    }
    if (min != null && max != null) {
      return "₹" + min + " - ₹" + max;
    }
    if (min != null) {
      return "₹" + min + "+";
    }
    return "Up to ₹" + max;
  }
}
