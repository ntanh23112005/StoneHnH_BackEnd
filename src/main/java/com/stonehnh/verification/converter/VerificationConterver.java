package com.stonehnh.verification.converter;

import com.stonehnh.verification.dto.request.CreationVerificationDto;
import com.stonehnh.verification.dto.response.VerificationResponseDto;
import com.stonehnh.verification.entity.Verification;

import java.util.ArrayList;
import java.util.List;

public class VerificationConterver {
    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return List verification đã được convert từ entity -> dto
     */
    public static List<VerificationResponseDto> toDtoList(List<Verification> verifications) {
        List<VerificationResponseDto> dtoList = new ArrayList<>();

        for (Verification v : verifications) {
            VerificationResponseDto dto = new VerificationResponseDto();
            dto.setId(v.getId());
            dto.setCustomerId(v.getCustomerId());
            dto.setAuthenicationCode(v.getAuthenicationCode());
            dto.setCreatedTime(v.getCreatedTime());
            dto.setIsVerified(v.getIsVerified());

            dtoList.add(dto);
        }

        return dtoList;
    }

    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return 1 verification đã được convert từ entity -> dto
     */
    public static VerificationResponseDto toDto(Verification verification) {
        return VerificationResponseDto.builder()
                .id(verification.getId())
                .customerId(verification.getCustomerId())
                .authenicationCode(verification.getAuthenicationCode())
                .createdTime(verification.getCreatedTime())
                .isVerified(verification.getIsVerified())
                .build();
    }

    /**
     * Hàm convert data từ frontend xuống entity backend để làm việc với database
     *
     * @return 1 verification đã được convert từ dto -> entity
     */
    public static Verification toEntity(CreationVerificationDto dto) {
        Verification verification = new Verification();
        verification.setId(dto.getId());
        verification.setCustomerId(dto.getCustomerId());
        verification.setAuthenicationCode(dto.getAuthenicationCode());
        verification.setCreatedTime(dto.getCreatedTime());
        verification.setIsVerified(dto.getIsVerified());

        return verification;
    }
}
