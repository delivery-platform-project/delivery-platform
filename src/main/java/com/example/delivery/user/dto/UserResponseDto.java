package com.example.delivery.user.dto;

import com.example.delivery.review.dto.response.FileResponseDTO;
import com.example.delivery.user.entity.User;
import com.example.delivery.user.entity.UserRoleEnum;
import java.time.LocalDateTime;

import lombok.Getter;
import org.springframework.http.MediaType;

@Getter
public class UserResponseDto {

    private Long userId;
    private String userName;
    private String email;
    private String streetAddress;
    private String detailAddress;
    private String phoneNum;
    private UserRoleEnum role;
    private byte[] profileImage;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
    private MediaType extensionAndGetMediaType;

    public UserResponseDto(User user, FileResponseDTO profileImage) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.streetAddress = user.getStreetAddress();
        this.detailAddress = user.getDetailAddress();
        this.phoneNum = user.getPhoneNum();
        this.role = user.getRole();
        this.profileImage = profileImage.getFileByte();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getUpdatedAt();
        this.deletedAt = user.getDeletedAt();
        this.extensionAndGetMediaType = profileImage.getExtensionAndGetMediaType();
    }
}