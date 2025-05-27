package com.stonehnh.mapper;

import com.stonehnh.entity.Owner;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OwnerMapper {
    /**
     * Lấy danh sách tất cả chủ sở hữu homestay.
     *
     * @return Danh sách các đối tượng Owner.
     */
    @Select("SELECT customer_id, homestay_id, percentage_own FROM owners")
    List<Owner> findAllOwners();

    /**
     * Lấy danh sách các homestay mà một khách hàng đang sở hữu.
     *
     * @param customerId Mã khách hàng.
     * @return Danh sách các đối tượng Owner.
     */
    @Select("SELECT customer_id, homestay_id, percentage_own FROM owners WHERE customer_id = #{customerId}")
    List<Owner> findOwnersByCustomerId(@Param("customerId") String customerId);

    /**
     * Lấy danh sách chủ sở hữu của một homestay.
     *
     * @param homestayId Mã homestay.
     * @return Danh sách các đối tượng Owner.
     */
    @Select("SELECT customer_id, homestay_id, percentage_own FROM owners WHERE homestay_id = #{homestayId}")
    List<Owner> findOwnersByHomestayId(@Param("homestayId") String homestayId);

    /**
     * Lấy thông tin sở hữu cụ thể theo mã khách hàng và homestay.
     *
     * @param customerId Mã khách hàng.
     * @param homestayId Mã homestay.
     * @return Đối tượng Owner nếu tồn tại, null nếu không.
     */
    @Select("SELECT customer_id, homestay_id, percentage_own FROM owners " +
            "WHERE customer_id = #{customerId} AND homestay_id = #{homestayId}")
    Owner findOwner(@Param("customerId") String customerId, @Param("homestayId") String homestayId);

    /**
     * Thêm mới một quan hệ sở hữu homestay.
     *
     * @param owner Đối tượng Owner chứa thông tin cần thêm.
     * @return Số dòng bị ảnh hưởng.
     */
    @Insert("INSERT INTO owners (customer_id, homestay_id, percentage_own) " +
            "VALUES (#{owner.customerId}, #{owner.homestayId}, #{owner.percentageOwn})")
    int insertOwner(@Param("owner") Owner owner);

    /**
     * Cập nhật phần trăm sở hữu của một khách hàng đối với một homestay.
     *
     * @param owner Đối tượng Owner chứa thông tin cập nhật.
     * @return Số dòng bị ảnh hưởng.
     */
    @Update("UPDATE owners SET percentage_own = #{owner.percentageOwn} " +
            "WHERE customer_id = #{owner.customerId} AND homestay_id = #{owner.homestayId}")
    int updateOwner(@Param("owner") Owner owner);

    /**
     * Xóa một quan hệ sở hữu giữa khách hàng và homestay.
     *
     * @param customerId Mã khách hàng.
     * @param homestayId Mã homestay.
     * @return Số dòng bị ảnh hưởng.
     */
    @Delete("DELETE FROM owners WHERE customer_id = #{customerId} AND homestay_id = #{homestayId}")
    int deleteOwner(@Param("customerId") String customerId, @Param("homestayId") String homestayId);

    /**
     * Xóa tất cả chủ sở hữu của một homestay.
     *
     * @param homestayId Mã homestay.
     * @return Số dòng bị ảnh hưởng.
     */
    @Delete("DELETE FROM owners WHERE homestay_id = #{homestayId}")
    int deleteOwnersByHomestayId(@Param("homestayId") String homestayId);

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM Owner WHERE customerId = #{customerId}")
    boolean existsOwner(@Param("customerId") String customerId);

}
