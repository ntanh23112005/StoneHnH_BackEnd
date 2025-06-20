package com.stonehnh.owner.service;

import com.stonehnh.owner.dto.request.CreationOwnerDto;
import com.stonehnh.owner.dto.response.OwnerResponseDto;

import java.util.List;

public interface OwnerService {
    /**
     * Lấy tất cả quan hệ sở hữu homestay
     * @return Danh sách OwnerDto
     */
//    List<OwnerResponseDto> getAllOwners();

    /**
     * Lấy danh sách homestay do 1 customer sở hữu
     * @param customerId ID của customer
     * @return Danh sách OwnerDto
     */
//    List<OwnerResponseDto> getHomestaysByCustomerId(String customerId);

    /**
     * Lấy danh sách chủ sở hữu của 1 homestay
     * @param homestayId ID của homestay
     * @return Danh sách OwnerDto
     */
    OwnerResponseDto getOwnersByHomestayId(String homestayId);

    /**
     * Thêm mới thông tin sở hữu
     * @param ownerDto Thông tin chủ sở hữu
     * @return Số dòng bị ảnh hưởng
     */
    int createOwner(CreationOwnerDto ownerDto);

    /**
     * Cập nhật tỉ lệ sở hữu
     * @param customerId ID customer
     * @return Số dòng bị ảnh hưởng
     */
    int updateOwnership(String customerId, CreationOwnerDto dto);

    /**
     * Xóa thông tin sở hữu
     * @param customerId ID customer
     * @param homestayId ID homestay
     * @return Số dòng bị ảnh hưởng
     */
    int deleteOwner(String customerId, String homestayId);
}
