package com.example.delivery.review.dto.response;

import com.example.delivery.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEditResponseDTO {
  private String content;
  private int starRating;
  private LocalDateTime updatedAt;
  private String userName;
  private String reviewImage;
  private MediaType extensionAndGetMediaType;

  public ReviewEditResponseDTO(Review review, FileResponseDTO reviewImagePath) {
    this.content = review.getContent();
    this.starRating = review.getStarRating();
    this.updatedAt = review.getUpdatedAt();
    this.userName = review.getUser().getUserName();
    this.reviewImage = reviewImagePath.getBase64EncodedImage();
    this.extensionAndGetMediaType = reviewImagePath.getExtensionAndGetMediaType();
  }
}
