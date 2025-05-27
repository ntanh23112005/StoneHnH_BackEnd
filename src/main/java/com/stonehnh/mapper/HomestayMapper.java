package com.stonehnh.mapper;

import com.stonehnh.entity.Homestay;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HomestayMapper {
    /**
     * Lấy danh sách tất cả Homestay trong bảng homestay
     *
     * @return Danh sách thông tin của tất cả homestay
     */
    @Select("SELECT homestay_id, homestay_name, country_address, area_address, detail_address, " +
            "daily_price, hourly_price, type, status, type_style, have_pet, max_customer, " +
            "number_of_beds, number_of_bathrooms, conveniences, options, entrance_parking, " +
            "bedroom_details, bathroom_details, support_equipments " +
            "FROM homestays")
    List<Homestay> findAllHomestays();

    /**
     * Thêm một Homestay mới
     *
     * @return Số dòng bị ảnh hưởng
     */
    @Insert("INSERT INTO homestays (homestay_id, homestay_name, country_address, area_address, detail_address, " +
            "daily_price, hourly_price, type, status, type_style, have_pet, max_customer, " +
            "number_of_beds, number_of_bathrooms, conveniences, options, entrance_parking, " +
            "bedroom_details, bathroom_details, support_equipments) " +
            "VALUES (#{homestay.homestayId}, #{homestay.homestayName}, #{homestay.countryAddress}, #{homestay.areaAddress}, #{homestay.detailAddress}, " +
            "#{homestay.dailyPrice}, #{homestay.hourlyPrice}, #{homestay.type}, #{homestay.status}, #{homestay.typeStyle}, " +
            "#{homestay.havePet}, #{homestay.maxCustomer}, #{homestay.numberOfBeds}, #{homestay.numberOfBathrooms}, #{homestay.conveniences}, " +
            "#{homestay.options}, #{homestay.entranceParking}, #{homestay.bedroomDetails}, #{homestay.bathroomDetails}, #{homestay.supportEquipments})")
    int insertHomestay(@Param("homestay") Homestay homestay);

    /**
     * Cập nhật thông tin Homestay
     *
     * @return Số dòng bị ảnh hưởng
     */
    @Update("UPDATE homestays SET " +
            "homestay_name = #{homestay.homestayName}, " +
            "country_address = #{homestay.countryAddress}, " +
            "area_address = #{homestay.areaAddress}, " +
            "detail_address = #{homestay.detailAddress}, " +
            "daily_price = #{homestay.dailyPrice}, " +
            "hourly_price = #{homestay.hourlyPrice}, " +
            "type = #{homestay.type}, " +
            "status = #{homestay.status}, " +
            "type_style = #{homestay.typeStyle}, " +
            "have_pet = #{homestay.havePet}, " +
            "max_customer = #{homestay.maxCustomer}, " +
            "number_of_beds = #{homestay.numberOfBeds}, " +
            "number_of_bathrooms = #{homestay.numberOfBathrooms}, " +
            "conveniences = #{homestay.conveniences}, " +
            "options = #{homestay.options}, " +
            "entrance_parking = #{homestay.entranceParking}, " +
            "bedroom_details = #{homestay.bedroomDetails}, " +
            "bathroom_details = #{homestay.bathroomDetails}, " +
            "support_equipments = #{homestay.supportEquipments} " +
            "WHERE homestay_id = #{homestay.homestayId}")
    int updateHomestay(@Param("homestay") Homestay homestay);

    /**
     * Xoá Homestay theo ID
     */
    @Delete("DELETE FROM homestays WHERE homestay_id = #{homestayId}")
    int deleteHomestayById(@Param("homestayId") String homestayId);

    /**
     * Lấy thông tin Homestay theo ID
     *
     * @param homestayId Mã Homestay cần tìm
     * @return Thông tin Homestay nếu tồn tại, ngược lại trả về null
     */
    @Select("SELECT homestay_id, homestay_name, country_address, area_address, detail_address, " +
            "daily_price, hourly_price, type, status, type_style, have_pet, max_customer, " +
            "number_of_beds, number_of_bathrooms, conveniences, options, entrance_parking, " +
            "bedroom_details, bathroom_details, support_equipments " +
            "FROM homestays WHERE homestay_id = #{homestayId}")
    Homestay findHomestayById(@Param("homestayId") String homestayId);

    /**
     * Kiểm tra sự tồn tại của Homestay theo ID
     *
     * @param homestayId Mã Homestay cần kiểm tra
     * @return true nếu tồn tại, false nếu không
     */
    @Select("SELECT COUNT(*) > 0 FROM homestays WHERE homestay_id = #{homestayId}")
    boolean isExistedHomestayById(@Param("homestayId") String homestayId);
}
