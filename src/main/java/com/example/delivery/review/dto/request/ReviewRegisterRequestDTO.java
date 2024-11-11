package com.example.delivery.review.dto.request;

import com.example.delivery.review.entity.Review;
import com.example.delivery.store.entity.Store;
import com.example.delivery.user.entity.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReviewRegisterRequestDTO {


  private String content;

  @NotNull
  @Min(1)
  @Max(5)
  private int starRating;

  @NotNull
  private UUID storeId;

  public Review toEntity(User user, Store store, String reviewImagePath) {
    return Review.builder()
        .content(this.getContent())
        .starRating(this.getStarRating())
        .user(user)
        .reviewImage(reviewImagePath)
        .build();
  }

}
