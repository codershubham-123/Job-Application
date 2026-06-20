package com.skywins.Job.Application.review.impl;

import com.skywins.Job.Application.company.Company;
import com.skywins.Job.Application.company.CompanyService;
import com.skywins.Job.Application.review.Review;
import com.skywins.Job.Application.review.ReviewRepository;
import com.skywins.Job.Application.review.ReviewService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceimpl implements ReviewService {
  private ReviewRepository reviewRepository;
  private CompanyService companyService;

  public ReviewServiceimpl(ReviewRepository reviewRepository, CompanyService companyService) {
    this.reviewRepository = reviewRepository;
    this.companyService = companyService;
  }

  @Override
  public List<Review> getAllReviews() {
    return reviewRepository.findAll();
  }

  @Override
  public List<Review> getAllReviews(Long companyId) {
    List<Review> reviews = reviewRepository.findByCompanyId(companyId);
    return reviews;
  }

  @Override
  public boolean addReview(Long companyId, Review review) {
    Company company = companyService.getCompanyById(companyId);
    if (company != null) {
      review.setCompany(company);
      reviewRepository.save(review);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Review getReview(Long companyId, Long reviewId) {
    List<Review> reviews = reviewRepository.findByCompanyId(companyId);
    return reviews.stream()
        .filter(review -> review.getId().equals(reviewId))
        .findFirst()
        .orElse(null);
  }

  @Override
  public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
    Review existingReview = getReview(companyId, reviewId);
    if (existingReview == null) {
      return false;
    }
    existingReview.setTitle(updatedReview.getTitle());
    existingReview.setDescription(updatedReview.getDescription());
    existingReview.setRating(updatedReview.getRating());
    existingReview.setPros(updatedReview.getPros());
    existingReview.setCons(updatedReview.getCons());
    existingReview.setReviewerRole(updatedReview.getReviewerRole());
    existingReview.setEmploymentStatus(updatedReview.getEmploymentStatus());
    if (updatedReview.getCreatedAt() != null) {
      existingReview.setCreatedAt(updatedReview.getCreatedAt());
    }
    reviewRepository.save(existingReview);
    return true;
  }

  @Override
  public boolean deleteReview(Long companyId, Long reviewId) {
    Review review = getReview(companyId, reviewId);
    if (review == null) {
      return false;
    }
    Company company = review.getCompany();
    if (company != null && company.getReviews() != null) {
      company.getReviews().remove(review);
    }
    review.setCompany(null);
    reviewRepository.deleteById(reviewId);
    return true;
  }
}
