package com.example.delivery.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {
  @Value("${upload.path}")
  private String uploadRootPath;
  // db에 저장될 이미지
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

  // 실제 저장된 이미지 경로
  public byte[] getImagePath(String profileImagePath) {
    String filePath = uploadRootPath + "/" + profileImagePath;
    File filePathFile = new File(filePath);
    try {
      return FileCopyUtils.copyToByteArray(filePathFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  // 미디어 타입
  public MediaType findExtensionAndGetMediaType(String profileImage) {
    String ext = profileImage.substring(profileImage.lastIndexOf(".") + 1);
    switch (ext.toUpperCase()) {
     case  "JPG": case "JPEG":
       return MediaType.IMAGE_JPEG;
      case "PNG":
        return MediaType.IMAGE_PNG;
        case "GIF":
          return MediaType.IMAGE_GIF;
      default:
        return null;
    }
  }
}
