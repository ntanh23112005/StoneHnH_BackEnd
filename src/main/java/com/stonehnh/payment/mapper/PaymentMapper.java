package com.stonehnh.payment.mapper;

import com.stonehnh.payment.dto.response.PaymentWithDetailDto;
import com.stonehnh.payment.entity.Payment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaymentMapper {
    /**
     * Lấy danh sách tất cả các thanh toán.
     *
     * @return Danh sách các đối tượng Payment.
     */
    @Select("SELECT payment_id, booking_id, payment_name, created_time, status FROM payments")
    List<Payment> findAllPayments();

    /**
     * Tìm thông tin thanh toán theo mã thanh toán.
     *
     * @param paymentId Mã thanh toán cần tìm.
     * @return Đối tượng Payment nếu tồn tại, null nếu không.
     */
    @Select("SELECT payment_id, booking_id, payment_name, created_time, status FROM payments WHERE payment_id = #{paymentId}")
    Payment findPaymentById(@Param("paymentId") String paymentId);

    /**
     * Lấy danh sách thanh toán liên quan đến một booking cụ thể.
     *
     * @param bookingId Mã booking.
     * @return Danh sách các đối tượng Payment.
     */
    @Select("SELECT payment_id, booking_id, payment_name, created_time, status FROM payments WHERE booking_id = #{bookingId}")
    List<Payment> findPaymentsByBookingId(@Param("bookingId") String bookingId);

    /**
     * Kiểm tra một thanh toán có tồn tại theo ID hay không.
     *
     * @param paymentId Mã thanh toán.
     * @return true nếu tồn tại, false nếu không.
     */
    @Select("SELECT COUNT(*) > 0 FROM payments WHERE payment_id = #{paymentId}")
    boolean isPaymentExists(@Param("paymentId") String paymentId);

    /**
     * Thêm một thanh toán mới vào cơ sở dữ liệu.
     *
     * @param payment Đối tượng Payment cần thêm.
     * @return Số dòng bị ảnh hưởng (1 nếu thành công).
     */
    @Insert("INSERT INTO payments (payment_id, booking_id, payment_name, created_time, status) " +
            "VALUES (#{payment.paymentId}, #{payment.bookingId}, #{payment.paymentName}, #{payment.createdTime}, #{payment.status})")
    int insertPayment(@Param("payment") Payment payment);

    /**
     * Cập nhật thông tin của một thanh toán đã có.
     *
     * @param payment Đối tượng Payment chứa dữ liệu cập nhật.
     * @return Số dòng bị ảnh hưởng.
     */
    @Update("UPDATE payments SET booking_id = #{payment.bookingId}, payment_name = #{payment.paymentName}, " +
            "created_time = #{payment.createdTime}, status = #{payment.status} WHERE payment_id = #{payment.paymentId}")
    int updatePayment(@Param("payment") Payment payment);

    /**
     * Xóa một thanh toán theo mã.
     *
     * @param paymentId Mã thanh toán.
     * @return Số dòng bị ảnh hưởng.
     */
    @Delete("DELETE FROM payments WHERE payment_id = #{paymentId}")
    int deletePaymentById(@Param("paymentId") String paymentId);

    /**
     * Xóa tất cả các thanh toán liên quan đến một booking.
     *
     * @param bookingId Mã booking.
     * @return Số dòng bị ảnh hưởng.
     */
    @Delete("DELETE FROM payments WHERE booking_id = #{bookingId}")
    int deletePaymentsByBookingId(@Param("bookingId") String bookingId);

    /**
     * Kiểm tra xem một payment (thanh toán) có tồn tại trong cơ sở dữ liệu hay không
     * dựa trên paymentId được truyền vào.
     *
     * @param paymentId ID của payment cần kiểm tra.
     * @return true nếu payment tồn tại, ngược lại trả về false.
     */
    @Select("SELECT COUNT(1) FROM Payment WHERE paymentId = #{paymentId}")
    boolean isExistedPaymentById(@Param("paymentId") String paymentId);

    @Select("""
    SELECT 
        b.booking_id,
        bd.booking_time AS created_time,
        b.payment_status AS status,
        b.total_price,

        bd.homestay_id,
        bd.booking_time,
        bd.check_in_time,
        bd.check_out_time,
        bd.number_of_customers,
        bd.number_of_pets,

        h.homestay_name

    FROM bookings b
    JOIN booking_details bd ON b.booking_id = bd.booking_id
    JOIN homestays h ON bd.homestay_id = h.homestay_id
    WHERE b.customer_id = #{customerId}
""")
    List<PaymentWithDetailDto> findPaymentsByCustomerId(@Param("customerId") String customerId);


}
