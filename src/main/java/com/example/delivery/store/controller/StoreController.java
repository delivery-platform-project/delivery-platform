package com.example.delivery.store.controller;

import com.example.delivery.auth.security.UserDetailsImpl;
import com.example.delivery.store.dto.CategoryRequestDto;
import com.example.delivery.store.dto.StoreRequestDto;
import com.example.delivery.store.dto.StoreResponseDto;
import com.example.delivery.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // 가게 등록
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('MANAGER', 'MASTER')")
    public ResponseEntity<String> createStore(@Valid @RequestBody StoreRequestDto storeRequestDto) {
        storeService.createStore(storeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("가게가 성공적으로 등록되었습니다.");
    }

    //가게 전체 페이지(키워드 및 카테고리 검색)
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<StoreResponseDto.GetStoresResponseDto>> getStores(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "true") boolean isAsc,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "category", required = false) String categoryName) {

        Page<StoreResponseDto.GetStoresResponseDto> result = storeService.getStores(page - 1, size, sortBy, isAsc, keyword, categoryName);
        return ResponseEntity.ok(result);
    }

    //가게 상세 페이지 조회(가게정보 및 메뉴(메뉴이름, 메뉴사진, 가격)리스트)
    @GetMapping("/{storeId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<StoreResponseDto.GetStoreDetailsResponseDto> getStoreDetails(
            @PathVariable UUID storeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "true") boolean isAsc,
            @RequestParam(value = "keyword", required = false) String keyword) {

        StoreResponseDto.GetStoreDetailsResponseDto storeResponseDto = storeService.getStoreDetails(storeId, page, size, sortBy, isAsc, keyword);
        return ResponseEntity.ok(storeResponseDto);
    }

    // 가게 수정
    @PutMapping("/{storeId}/update")
    @PreAuthorize("hasAnyRole('OWNER', 'MANAGER', 'MASTER')")
    public ResponseEntity<String> updateStore(@PathVariable UUID storeId, @Valid @RequestBody StoreRequestDto storeRequestDto) {
        storeService.updateStore(storeRequestDto, storeId);
        return ResponseEntity.ok("가게 정보가 성공적으로 수정되었습니다.");
    }

    // 가게 삭제
    @DeleteMapping("/{storeId}/delete")
    @PreAuthorize("hasAnyRole('MANAGER', 'MASTER')")
    public ResponseEntity<String> deleteStore(@PathVariable UUID storeId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        storeService.deleteStore(storeId, userDetails.getUsername());
        return ResponseEntity.ok("가게가 성공적으로 삭제되었습니다.");
    }

    // 가게 카테고리 등록
    @PostMapping("/category/create")
    @PreAuthorize("hasAnyRole('MANAGER', 'MASTER')")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        storeService.createCategory(categoryRequestDto);
        return ResponseEntity.ok("카테고리가 성공적으로 등록되었습니다.");
    }

    // 가게 카테고리 수정
    @PutMapping("/category/update")
    @PreAuthorize("hasAnyRole('MANAGER', 'MASTER')")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        storeService.updateCategory(categoryRequestDto);
        return ResponseEntity.ok("카테고리가 성공적으로 수정되었습니다.");
    }

    // 가게 카테고리 삭제
    @DeleteMapping("/category/delete")
    @PreAuthorize("hasAnyRole('MANAGER', 'MASTER')")
    public ResponseEntity<String> deleteCategory(@RequestBody UUID categoryId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        storeService.deleteCategory(categoryId, userDetails.getUsername());
        return ResponseEntity.ok("카테고리가 성공적으로 삭제되었습니다.");
    }

}