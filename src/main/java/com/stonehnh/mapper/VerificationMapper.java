package com.stonehnh.mapper;
import com.stonehnh.entity.Verification;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface VerificationMapper {
    /**
     * Lấy danh sách tất cả Verification trong bảng customers
     *
     * @return Danh sách thông tin của tất cả Verification
     */
    @Select("SELECT id, customer_id, authenication_code, created_time, is_verified FROM verifications")
    List<Verification> findAllVerifications();
}
