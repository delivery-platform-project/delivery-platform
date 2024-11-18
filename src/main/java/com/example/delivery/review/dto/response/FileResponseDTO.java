package com.example.delivery.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponseDTO {
  private byte [] fileByte;
  private MediaType extensionAndGetMediaType;
}