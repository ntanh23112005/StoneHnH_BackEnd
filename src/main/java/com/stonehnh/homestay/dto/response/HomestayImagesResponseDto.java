package com.stonehnh.homestay.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomestayImagesResponseDto {
    private int id;
    private String homestayId;
    private String homestayImage;
    private String imageFor;
}
