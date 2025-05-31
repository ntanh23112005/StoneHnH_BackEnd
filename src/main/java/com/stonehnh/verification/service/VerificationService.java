package com.stonehnh.verification.service;

import com.stonehnh.verification.dto.request.CreationVerificationDto;
import com.stonehnh.verification.dto.response.VerificationResponseDto;

import java.util.List;

public interface VerificationService {
    /**
     * Lấy danh sách tất cả verification
     *
     * @return Danh sách verification DTO
     */
    List<VerificationResponseDto> findAll();

    /**
     * Tìm verification theo ID
     *
     * @param id mã ID của verification
     * @return Verification DTO nếu tìm thấy, ngược lại trả về null
     */
   // VerificationResponseDto findById(int id);

    /**
     * Thêm mới một verification
     *
     * @param dto Dữ liệu gửi từ frontend
     * @return true nếu thêm thành công, false nếu thất bại
     */
    //boolean insert(CreationVerificationDto dto);

    /**
     * Cập nhật một verification đã tồn tại
     *
     * @param dto Dữ liệu đã cập nhật
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    //boolean update(CreationVerificationDto dto);

    /**
     * Xoá verification theo ID
     *
     * @param id Mã ID cần xoá
     * @return true nếu xoá thành công, false nếu thất bại
     */
    //boolean deleteById(int id);

    /**
     * Kiểm tra verification có tồn tại theo ID hay không
     *
     * @param id Mã ID
     * @return true nếu tồn tại, ngược lại false
     */
   // boolean isExistById(int id);
}
