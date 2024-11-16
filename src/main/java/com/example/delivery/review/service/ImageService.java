package com.example.delivery.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {
  @Value("${upload.path}")
  private String uploadRootPath;
  public String uploadProfileImage(MultipartFile originalFile) {

    if (originalFile == null) {
      return null;
    }

    File rootDir = new File(uploadRootPath);
    if (!rootDir.exists()) {
      rootDir.mkdir();
    }
    String uniqueFileName = UUID.randomUUID() +
        "_" + originalFile.getOriginalFilename();

    File uploadFile = new File(uploadRootPath + "/" + uniqueFileName);
    try {
      originalFile.transferTo(uploadFile);
      return uniqueFileName;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
