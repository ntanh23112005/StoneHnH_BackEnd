package com.stonehnh.homestay.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomestayImages {
    private int id;
    private String homestayId;
    private String homestayImage;
    private String imageFor;
}
