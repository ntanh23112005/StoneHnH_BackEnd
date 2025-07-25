package com.stonehnh.homestay.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class CreationHomestayImagesDto {
    private int id;
    private String homestayId;
    private String homestayName;
    private String homestayImage;
    private String imageFor;
    private List<MultipartFile> represent;
    private List<MultipartFile> catalog;
}