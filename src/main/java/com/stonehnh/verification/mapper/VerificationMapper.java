package com.stonehnh.verification.mapper;
import com.stonehnh.verification.entity.Verification;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface VerificationMapper {
    /**
     * Lấy tất cả bản ghi xác thực
     * @return Danh sách các bản ghi xác thực
     */
    @Select("SELECT id, customer_id, authenication_code, created_time, is_verified FROM verification")
    List<Verification> findAllVerifications();

    /**
     * Tìm bản ghi xác thực theo ID
     * @param id Mã xác thực
     * @return Bản ghi xác thực nếu tìm thấy, ngược lại trả về null
     */
    @Select("SELECT id, customer_id, authenication_code, created_time, is_verified FROM verification WHERE id = #{id}")
    Verification findVerificationById(@Param("id") int id);

    /**
     * Thêm bản ghi xác thực mới
     * @param verification Đối tượng Verification
     * @return Số dòng bị ảnh hưởng
     */
    @Insert("""
        INSERT INTO verification (customer_id, authenication_code, created_time, is_verified)
        VALUES (#{verification.customerId}, #{verification.authenicationCode}, #{verification.createdTime}, #{verification.isVerified})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "verification.id")
    int insertVerification(@Param("verification") Verification verification);

    /**
     * Cập nhật bản ghi xác thực
     * @param verification Đối tượng Verification đã cập nhật
     * @return Số dòng bị ảnh hưởng
     */
    @Update("""
        UPDATE verification
        SET customer_id = #{verification.customerId},
            authenication_code = #{verification.authenicationCode},
            created_time = #{verification.createdTime},
            is_verified = #{verification.isVerified}
        WHERE id = #{verification.id}
    """)
    int updateVerification(@Param("verification") Verification verification);

    /**
     * Xoá bản ghi xác thực theo ID
     * @param id Mã xác thực cần xoá
     * @return Số dòng bị ảnh hưởng
     */
    @Delete("DELETE FROM verification WHERE id = #{id}")
    int deleteVerificationById(@Param("id") int id);

    /**
     * Kiểm tra bản ghi xác thực có tồn tại không theo ID
     * @param id Mã xác thực
     * @return true nếu tồn tại, ngược lại false
     */
    @Select("SELECT COUNT(*) > 0 FROM verification WHERE id = #{id}")
    boolean isExistedVerificationById(@Param("id") int id);
}
