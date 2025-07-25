package com.stonehnh.homestay.controller;

import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.homestay.dto.request.CreationHomestayImagesDto;
import com.stonehnh.homestay.service.HomestayImageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/homestay-image")
public class HomestayImageController {

    private final HomestayImageService homestayImageService;

    public HomestayImageController(HomestayImageService homestayImageService) {
        this.homestayImageService = homestayImageService;
    }

    /**
     * API lấy tất cả hình ảnh của một homestay
     *
     * @return Danh sách hình ảnh của homestay
     */
//    @GetMapping("/{id}")
//    public ApiResponse<Object> getAllHomestayImages(@PathVariable int homestayId) {
//        return ApiResponse.builder()
//                .success(true)
//                .message("Lấy danh sách homestay thành công")
//                .data(homestayImageService.getAllImages())
//                .build();
//    }

    /**
     * API thêm một hình ảnh mới cho homestay
     *
     * @return Thông tin hình ảnh đã thêm
     */
//    @PostMapping
//    public ApiResponse<Object> createNewHomestayImage(CreationHomestayImagesDto creationHomestayImagesDto) {
//        return ApiResponse.builder()
//                .success(true)
//                .message("Thêm hình ảnh homestay thành công")
//                .data(homestayImageService.createImage(creationHomestayImagesDto))
//                .build();
//    }
//
//    /**
//     * API cập nhật thông tin một hình ảnh hiện có.
//     *
//     * @return Thông tin hình ảnh đã cập nhật.
//     */
//    @PutMapping("/{id}")
//    public ApiResponse<Object> updateHomestayImages(int id, CreationHomestayImagesDto creationHomestayImagesDto) {
//        return ApiResponse.builder()
//                .success(true)
//                .message("Cập nhật hình ảnh homestay thành công")
//                .data(homestayImageService.updateImage(id, creationHomestayImagesDto))
//                .build();
//    }
//
//    /**
//     * API xóa một hình ảnh của homestay
//     *
//     * @return Kết quả xóa
//     */
//    @DeleteMapping("/{imageId}")
//    public ApiResponse<Object> deleteHomestayImage(@PathVariable("imageId") String imageId) {
//        return ApiResponse.builder()
//                .success(true)
//                .message("Xóa hình ảnh homestay thành công")
//                .data(homestayImageService.findImageById(Integer.parseInt(imageId)))
//                .build();
//    }
//
//    /**
//     * API lấy thông tin một hình ảnh cụ thể bằng ID của nó.
//     *
//     * @return Thông tin hình ảnh.
//     */
//    @GetMapping("/{imageId}")
//    public ApiResponse<Object> getHomestayImageById(@PathVariable("imageId") String imageId) {
//        return ApiResponse.builder()
//                .success(true)
//                .message("Lấy thông tin hình ảnh thành công")
//                .data(homestayImageService.findImageById(Integer.parseInt(imageId)))
//                .build();
//    }

    /**
     * API thêm hình ảnh mới
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<Object> createNewHomestayImageXP(
            @ModelAttribute CreationHomestayImagesDto dto
    ) {
        return ApiResponse.builder()
                .success(true)
                .message("Thêm hình ảnh homestay thành công")
                .data(homestayImageService.createImage(dto))
                .build();
    }

    /**
     * API cập nhật hình ảnh
     */
    @PutMapping("/{imageId}")
    public ApiResponse<Object> updateHomestayImageXP(
            @PathVariable int imageId,
            @RequestParam String homestayId,
            @RequestParam String homestayName,
            @RequestParam String imageFor,
            @RequestParam MultipartFile imageFile
    ) {
        CreationHomestayImagesDto dto = new CreationHomestayImagesDto();
        dto.setHomestayId(homestayId);
        dto.setHomestayName(homestayName);
        dto.setImageFor(imageFor);

        return ApiResponse.builder()
                .success(true)
                .message("Cập nhật hình ảnh homestay thành công")
                .data(homestayImageService.updateImage(imageId, dto))
                .build();
    }

    /**
     * API xóa hình ảnh
     */
    @DeleteMapping("/{imageId}")
    public ApiResponse<Object> deleteHomestayImageXP(@PathVariable int imageId) {
        return ApiResponse.builder()
                .success(true)
                .message("Xóa hình ảnh homestay thành công")
                .data(homestayImageService.deleteImage(imageId))
                .build();
    }

    /**
     * API lấy thông tin 1 hình ảnh
     */
    @GetMapping("/{imageId}")
    public ApiResponse<Object> getHomestayImageByIdXP(@PathVariable int imageId) {
        return ApiResponse.builder()
                .success(true)
                .message("Lấy thông tin hình ảnh thành công")
                .data(homestayImageService.findImageById(imageId))
                .build();
    }

    /**
     * API lấy danh sách ảnh theo homestayId
     */
    @GetMapping("/by-homestay/{homestayId}")
    public ApiResponse<Object> getImagesByHomestayIdXP(@PathVariable String homestayId) {
        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách hình ảnh homestay thành công")
                .data(homestayImageService.getImagesByHomestayId(homestayId))
                .build();
    }
}
