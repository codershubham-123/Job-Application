package com.skywins.Job.Application.company.dto;

import com.skywins.Job.Application.company.Company;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanySummaryResponse {
  private Long id;
  private String name;
  private String logoUrl;
  private String initial;

  public static CompanySummaryResponse from(Company company) {
    if (company == null) {
      return null;
    }
    return new CompanySummaryResponse(
        company.getId(), company.getName(), company.getLogoUrl(), buildInitial(company.getName()));
  }

  private static String buildInitial(String name) {
    if (name == null || name.isBlank()) {
      return null;
    }
    return name.trim().substring(0, 1).toUpperCase();
  }
}
