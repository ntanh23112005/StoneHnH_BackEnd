package com.stonehnh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // ===== Hệ thống chung =====
    SYSTEM_ERROR("1000", "Lỗi hệ thống. Vui lòng thử lại sau."),
    SQL_ERROR("1001", "Lỗi SQL. Vui lòng kiểm tra câu truy vấn."),
    UNEXPECTED_EXCEPTION("1002", "Lỗi không xác định."),

    // ===== Khách hàng =====
    CUSTOMER_CREATE_FAILED("2101", "Không thể tạo khách hàng."),
    CUSTOMER_UPDATE_FAILED("2201", "Không thể cập nhật thông tin khách hàng."),
    CUSTOMER_DELETE_FAILED("2301", "Không thể xóa khách hàng."),
    CUSTOMER_NOT_FOUND("2401", "Không tìm thấy khách hàng."),
    CUSTOMER_EMAIL_DUPLICATE("2402", "Email khách hàng đã tồn tại."),

    // ===== Homestay =====
    HOMESTAY_CREATE_FAILED("3101", "Không thể thêm homestay."),
    HOMESTAY_UPDATE_FAILED("3201", "Không thể cập nhật homestay."),
    HOMESTAY_DELETE_FAILED("3301", "Không thể xóa homestay."),
    HOMESTAY_NOT_FOUND("3401", "Không tìm thấy homestay."),
    HOMESTAY_STATUS_INVALID("3402", "Trạng thái homestay không hợp lệ."),

    // ===== Hình ảnh Homestay =====
    HOMESTAY_IMAGE_NOT_FOUND("3403", "Không tìm thấy hình ảnh homestay."),
    HOMESTAY_IMAGE_UPLOAD_FAILED("3102", "Tải ảnh homestay thất bại."),

    // ===== Luật Homestay =====
    HOMESTAY_RULE_NOT_FOUND("3404", "Không tìm thấy quy định homestay."),
    HOMESTAY_RULE_CREATE_FAILED("3103", "Thêm luật homestay thất bại."),

    // ===== Chủ sở hữu =====
    OWNER_NOT_FOUND("3405", "Không tìm thấy chủ sở hữu homestay."),
    OWNER_ASSIGN_FAILED("3104", "Gán chủ sở hữu thất bại."),

    // ===== Đặt phòng =====
    BOOKING_CREATE_FAILED("4101", "Không thể tạo đặt phòng."),
    BOOKING_NOT_FOUND("4401", "Không tìm thấy thông tin đặt phòng."),

    // ===== Thanh toán =====
    PAYMENT_NOT_FOUND("5401", "Không tìm thấy thanh toán."),
    PAYMENT_FAILED("5101", "Thanh toán thất bại."),

    // ===== Đánh giá & nhận xét =====
    REVIEW_NOT_FOUND("6401", "Không tìm thấy đánh giá."),
    RATE_NOT_FOUND("6402", "Không tìm thấy xếp hạng."),
    RATE_CREATE_FAILED("6101", "Tạo đánh giá thất bại."),

    // ===== AUTH =====
    INVALID_CREDENTIALS("AUTH_001", "Mật khẩu hoặc email không chính xác"),
    INVALID_REFRESH_TOKEN("AUTH_002", "Refresh token không hợp lệ"),
    MISSING_OR_INVALID_TOKEN("AUTH_003", "Thiếu token hoặc token sai định dạng"),
    INVALID_TOKEN("AUTH_004", "Token không hợp lệ"),
    MISSING_CREDENTIAL("AUTH_005", "Thiếu credential"),
    INVALID_ID_TOKEN("AUTH_006", "ID token không hợp lệ"),
    FAILED_TO_CREATE_ACCOUNT("AUTH_007", "Không thể tạo tài khoản mới"),
    SERVER_ERROR("AUTH_008", "Lỗi server"),

    // ===== SUCCESS MESSAGE =====
    LOGIN_SUCCESS("AUTH_100", "Đăng nhập thành công"),
    LOGOUT_SUCCESS("AUTH_101", "Đăng xuất thành công"),
    GET_ACCOUNT_SUCCESS("AUTH_102", "Lấy Account theo access-token thành công"),
    GOOGLE_LOGIN_SUCCESS("AUTH_103", "Đăng nhập Google thành công");

    private final String code;
    private final String message;
}
