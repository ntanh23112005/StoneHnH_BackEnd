# StoneHnH_BackEnd
Dự án Trang web Booking homestay view biển khắp Việt Name _ BackEnd

# BIG UPDATE ( 28/05/2025 )
### 1️⃣ Cập nhật lại toàn bộ package dự án ( package by layer ➡ package by feature )
### 2️⃣ Thay thế File config "application.properties" ➡ "application.yml"
### 3️⃣ Cập nhật thư mục "resources/static/mybatis": Chuẩn bị cho các truy vấn phức tạp
- **Thông tin các package by feature (com.stonehnh....) & Entity ảnh hưởng:**
    - **_booking_**: Booking, BookingDetail
    - **_customer_**: Customer, CustomerRole, Role
    - **_homestay_**: Homestay, HomestayImage, HomestayRule
    - **_owner_**: Owner
    - **_payment_**: Payment
    - **_review_**: Rate, Reviews
    - **_verification_**: Verification
    - **_common_**: Các package global (**_enums_**, **_exception_**, **_handler_**)



## LUỒNG CODE:
- Bước 1: Nhìn dữ liệu MySql, code "Entity"
- Bước 2: Code "Mapper" ( DAO)
- Bước 3: Code DTO (response, request)
- Bước 4: Code "converter"
- Bước 5: Code "Service"
- Bước 6: Code "ServiceImpl"
- Bước 7: Code Controller ( Lưu ý: Sử dụng cách call service và ApiResponse theo ví dụ mẫu "CustomerController")
+ Lưu ý: Ghi rõ câu lệnh SQL trong "Mapper", ghi đúng tên đúng chính tả


### LƯU Ý VỀ CÁC MÃ LỖI:
- Các mã lỗi nằm trong class Enum ErrorCode KHÔNG ĐƯỢC SỬA, mọi thắc mắc liên hệ Lead


