package com.stonehnh.review.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CreationRateDetailDto {
    private String rateId;
    private String homestayId;
    private String customerId;
    private String comments;
    private Date ratedTime;
    // Giá cả
    private float price;
    // Vị trí
    private int location;
    // Thái độ giao tiếp
    private float communication;
    // Độ chính xác so với mô tả
    private float exactly;
    // Mức độ sạch sẽ
    private float cleanlinessLevel;
    // Điểm trung bình
    private float averageRate;
    // >4.5 Tuyệt vời || <4.5 Không có
    private String rateTitle;
}
