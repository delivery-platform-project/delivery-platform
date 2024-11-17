package com.example.delivery.bookmark.dto;

import com.example.delivery.review.entity.Review;
import com.example.delivery.store.entity.Store;

import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class  BookmarkedStoreResponseDto{
    private UUID storeId;
    private String storeName;
    private double starRating;
    private boolean opened;


    public BookmarkedStoreResponseDto(Store store, List<Review> reviews) {
        this.storeId = store.getStoreId();
        this.storeName = store.getStoreName();
        this.starRating = CalStarRating(reviews);
        this.opened = store.isOpened();
    }

    public double CalStarRating(List<Review> reviews) {
        int sum = 0;
        for (Review review : reviews) {
            sum += review.getStarRating();
        }
        double average = (double) sum / reviews.size();
        return Math.round(average * 10) / 10.0;
    }
}