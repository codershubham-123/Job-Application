package com.skywins.Job.Application.review;

import com.skywins.Job.Application.common.ApiResponse;
import com.skywins.Job.Application.review.dto.ReviewResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies/{companyId}")
public class ReviewController {
  private ReviewService reviewService;

  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping("/reviews")
  public ResponseEntity<ApiResponse<List<ReviewResponse>>> getAllReview(
      @PathVariable Long companyId) {
    List<ReviewResponse> reviews =
        reviewService.getAllReviews(companyId).stream().map(ReviewResponse::from).toList();
    return new ResponseEntity<>(ApiResponse.of(reviews, reviews.size()), HttpStatus.OK);
  }

  @PostMapping("/reviews")
  public ResponseEntity<String> addReview(
      @PathVariable Long companyId, @RequestBody Review review) {
    boolean isReviewSaved = reviewService.addReview(companyId, review);
    if (isReviewSaved) {

      return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Review Not Saved", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/reviews/{reviewId}")
  public ResponseEntity<ReviewResponse> getReview(
      @PathVariable Long companyId, @PathVariable Long reviewId) {
    Review review = reviewService.getReview(companyId, reviewId);
    if (review == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(ReviewResponse.from(review), HttpStatus.OK);
  }

  @PutMapping("/reviews/{reviewId}")
  public ResponseEntity<String> updateReview(
      @PathVariable Long companyId,
      @PathVariable Long reviewId,
      @RequestBody Review updatedReview) {
    boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, updatedReview);
    if (isReviewUpdated) {

      return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/reviews/{reviewId}")
  public ResponseEntity<String> deleteReview(
      @PathVariable Long companyId, @PathVariable Long reviewId) {
    boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
    if (isReviewDeleted) {
      return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);

    } else {
      return new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);
    }
  }
}
