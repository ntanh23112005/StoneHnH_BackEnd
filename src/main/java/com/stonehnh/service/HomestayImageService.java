package com.stonehnh.service;

import com.stonehnh.dto.request.CreationHomestayImagesDto;
import com.stonehnh.dto.response.HomestayImagesResponseDto;

import java.util.List;

public interface HomestayImageService {
    /**
     * Lấy danh sách tất cả ảnh homestay
     * @return List ảnh dạng DTO
     */
//    List<HomestayImagesResponseDto> getAllImages();

    /**
     * Lấy danh sách ảnh của homestay theo homestayId
     * @param homestayId ID homestay
     * @return List ảnh dạng DTO
     */
    List<HomestayImagesResponseDto> getImagesByHomestayId(String homestayId);

    /**
     * Tạo mới một ảnh homestay
     * @param dto dữ liệu ảnh homestay
     * @return Số dòng bị ảnh hưởng
     */
    int createImage(CreationHomestayImagesDto dto);

    /**
     * Cập nhật ảnh homestay theo id
     * @param id id ảnh cần cập nhật
     * @param dto dữ liệu mới
     * @return Số dòng bị ảnh hưởng
     */
    int updateImage(int id, CreationHomestayImagesDto dto);

    /**
     * Xóa ảnh homestay theo id
     * @param id id ảnh cần xóa
     * @return Số dòng bị ảnh hưởng
     */
    int deleteImage(int id);

    /**
     * Tìm ảnh homestay theo id
     * @param id id ảnh cần tìm
     * @return ảnh dạng DTO
     */
    HomestayImagesResponseDto findImageById(int id);

}
