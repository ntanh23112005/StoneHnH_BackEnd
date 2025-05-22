package com.stonehnh.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreationHomestayImagesDto {
    private int id;
    private String homestayId;
    private String homestayImage;
    private String imageFor;
}
