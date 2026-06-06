package com.skywins.Job.Application.review;

import com.skywins.Job.Application.common.ApiResponse;
import com.skywins.Job.Application.review.dto.ReviewResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class AllReviewsController {
  private final ReviewService reviewService;

  public AllReviewsController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<ReviewResponse>>> getAllReviews() {
    List<ReviewResponse> reviews =
        reviewService.getAllReviews().stream().map(ReviewResponse::from).toList();
    return ResponseEntity.ok(ApiResponse.of(reviews, reviews.size()));
  }
}
