package com.skywins.Job.Application.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
  private T data;
  private MetaResponse meta;

  public static <T> ApiResponse<T> of(T data, long totalItems) {
    return new ApiResponse<>(data, new MetaResponse(totalItems));
  }
}
