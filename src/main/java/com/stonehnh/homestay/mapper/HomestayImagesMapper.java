package com.stonehnh.homestay.mapper;

import com.stonehnh.homestay.entity.HomestayImages;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HomestayImagesMapper {
    /**
     * Lấy danh sách tất cả ảnh của homestay.
     *
     * @return Danh sách các đối tượng HomestayImage chứa thông tin ảnh homestay.
     */
    @Select("SELECT id, homestay_id, homestay_image, image_for FROM homestay_images")
    List<HomestayImages> findAllImages();

    /**
     * Lấy thông tin ảnh homestay theo ID ảnh.
     *
     * @param id ID của ảnh homestay cần tìm.
     * @return Đối tượng HomestayImage tương ứng với ID, hoặc null nếu không tìm thấy.
     */
    @Select("SELECT id, homestay_id, homestay_image, image_for FROM homestay_images WHERE id = #{id}")
    HomestayImages findImageById(@Param("id") int id);

    /**
     * Lấy danh sách ảnh của một homestay theo mã homestay.
     *
     * @param homestayId Mã homestay cần lấy ảnh.
     * @return Danh sách các đối tượng HomestayImage thuộc homestay đó.
     */
    @Select("SELECT id, homestay_id, homestay_image, image_for FROM homestay_images WHERE homestay_id = #{homestayId}")
    List<HomestayImages> findImagesByHomestayId(@Param("homestayId") String homestayId);

    /**
     * Thêm mới một ảnh cho homestay.
     *
     * @param image Đối tượng HomestayImage chứa thông tin ảnh cần thêm.
     * @return Số bản ghi bị ảnh hưởng (thường là 1 nếu thành công).
     */
    @Insert("INSERT INTO homestay_images (id, homestay_id, homestay_image, image_for) " +
            "VALUES (#{image.id}, #{image.homestayId}, #{image.homestayImage}, #{image.imageFor})")
    int insertImage(@Param("image") HomestayImages image);

    /**
     * Cập nhật thông tin ảnh homestay theo ID ảnh.
     *
     * @param image Đối tượng HomestayImage chứa thông tin mới và ID ảnh cần cập nhật.
     * @return Số bản ghi bị ảnh hưởng (thường là 1 nếu thành công).
     */
    @Update("UPDATE homestay_images SET " +
            "homestay_id = #{image.homestayId}, " +
            "homestay_image = #{image.homestayImage}, " +
            "image_for = #{image.imageFor} " +
            "WHERE id = #{image.id}")
    int updateImage(@Param("image") HomestayImages image);

    /**
     * Xóa ảnh homestay theo ID ảnh.
     *
     * @param id ID của ảnh homestay cần xóa.
     * @return Số bản ghi bị ảnh hưởng (thường là 1 nếu thành công).
     */
    @Delete("DELETE FROM homestay_images WHERE id = #{id}")
    int deleteImageById(@Param("id") int id);

    /**
     * HÀNH ĐỘNG NGUY HIỂM !!!
     *
     * Xóa tất cả ảnh thuộc về một homestay theo mã homestay.
     *
     * @param homestayId Mã homestay mà ảnh thuộc về cần xóa.
     * @return Số bản ghi bị ảnh hưởng (số lượng ảnh đã xóa).
     */
    @Delete("DELETE FROM homestay_images WHERE homestay_id = #{homestayId}")
    int deleteImagesByHomestayId(@Param("homestayId") String homestayId);

    @Select("SELECT COUNT(*) > 0 FROM HomestayImages WHERE id = #{id}")
    boolean existsImageById(int id);
}
