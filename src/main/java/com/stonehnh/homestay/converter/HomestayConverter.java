package com.stonehnh.homestay.converter;

import com.stonehnh.homestay.dto.request.CreationHomestayDto;
import com.stonehnh.homestay.dto.response.HomestayResponseDto;
import com.stonehnh.homestay.entity.Homestay;

import java.util.ArrayList;
import java.util.List;

public class HomestayConverter {
    /**
     * Convert 1 Homestay entity thành HomestayDto để trả về frontend
     */
    public static HomestayResponseDto toDto(Homestay homestay) {
        return HomestayResponseDto.builder()
                .homestayId(homestay.getHomestayId())
                .homestayName(homestay.getHomestayName())
                .countryAddress(homestay.getCountryAddress())
                .areaAddress(homestay.getAreaAddress())
                .detailAddress(homestay.getDetailAddress())
                .dailyPrice(homestay.getDailyPrice())
                .hourlyPrice(homestay.getHourlyPrice())
                .type(homestay.getType())
                .status(homestay.isStatus())
                .typeStyle(homestay.getTypeStyle())
                .havePet(homestay.isHavePet())
                .maxCustomer(homestay.getMaxCustomer())
                .numberOfBeds(homestay.getNumberOfBeds())
                .numberOfBathrooms(homestay.getNumberOfBathrooms())
                .conveniences(homestay.getConveniences())
                .options(homestay.getOptions())
                .entranceParking(homestay.isEntranceParking())
                .bedroomDetails(homestay.getBedroomDetails())
                .bathroomDetails(homestay.getBathroomDetails())
                .supportEquipments(homestay.getSupportEquipments())
                .build();
    }

    /**
     * Convert List Homestay entity thành List HomestayDto
     */
    public static List<HomestayResponseDto> toDtoList(List<Homestay> homestays) {
        List<HomestayResponseDto> dtoList = new ArrayList<>();
        for (Homestay h : homestays) {
            dtoList.add(toDto(h));
        }
        return dtoList;
    }

    /**
     * Convert HomestayDto thành Homestay entity để lưu vào database
     */
    public static Homestay toEntity(CreationHomestayDto dto) {
        Homestay homestay = new Homestay();
        homestay.setHomestayId(dto.getHomestayId());
        homestay.setHomestayName(dto.getHomestayName());
        homestay.setCountryAddress(dto.getCountryAddress());
        homestay.setAreaAddress(dto.getAreaAddress());
        homestay.setDetailAddress(dto.getDetailAddress());
        homestay.setDailyPrice(dto.getDailyPrice());
        homestay.setHourlyPrice(dto.getHourlyPrice());
        homestay.setType(dto.getType());
        homestay.setStatus(dto.isStatus());
        homestay.setTypeStyle(dto.getTypeStyle());
        homestay.setHavePet(dto.isHavePet());
        homestay.setMaxCustomer(dto.getMaxCustomer());
        homestay.setNumberOfBeds(dto.getNumberOfBeds());
        homestay.setNumberOfBathrooms(dto.getNumberOfBathrooms());
        homestay.setConveniences(dto.getConveniences());
        homestay.setOptions(dto.getOptions());
        homestay.setEntranceParking(dto.isEntranceParking());
        homestay.setBedroomDetails(dto.getBedroomDetails());
        homestay.setBathroomDetails(dto.getBathroomDetails());
        homestay.setSupportEquipments(dto.getSupportEquipments());

        return homestay;
    }
}
