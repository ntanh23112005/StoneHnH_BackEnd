package com.stonehnh.verification.service.impl;

import com.stonehnh.verification.converter.VerificationConterver;
import com.stonehnh.verification.dto.request.CreationVerificationDto;
import com.stonehnh.verification.dto.response.VerificationResponseDto;
import com.stonehnh.verification.entity.Verification;
import com.stonehnh.verification.mapper.VerificationMapper;
import com.stonehnh.verification.service.VerificationService;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final VerificationMapper verificationMapper;

    public VerificationServiceImpl(VerificationMapper verificationMapper) {
        this.verificationMapper = verificationMapper;
    }

    @Override
    @Transactional (readOnly = true)
    public List<VerificationResponseDto> findAll() {
        try {
            return VerificationConterver.toDtoList(verificationMapper.findAllVerifications());
        } catch (Exception e) {
            throw new AppException(ErrorCode.UNEXPECTED_EXCEPTION);
        }
    }


//    @Override
//    public VerificationResponseDto findById(int id) {
//        Verification verification = verificationMapper.findVerificationById(id);
//
//        if (verification == null) {
//            throw new AppException(ErrorCode.VERIFICATION_NOT_FOUND);
//        }
//
//        return VerificationConverter.toDto(verification);
//    }
//
//    @Override
//    public boolean insert(CreationVerificationDto dto) {
//        Verification verification = VerificationConverter.toEntity(dto);
//        return verificationMapper.insertVerification(verification) > 0;
//    }
//
//    @Override
//    public boolean update(CreationVerificationDto dto) {
//        boolean exists = verificationMapper.isExistVerificationById(dto.getId());
//
//        if (!exists) {
//            throw new AppException(ErrorCode.VERIFICATION_NOT_FOUND);
//        }
//
//        Verification verification = VerificationConverter.toEntity(dto);
//        return verificationMapper.updateVerification(verification) > 0;
//    }
//
//    @Override
//    public boolean deleteById(int id) {
//        boolean exists = verificationMapper.isExistVerificationById(id);
//
//        if (!exists) {
//            throw new AppException(ErrorCode.VERIFICATION_NOT_FOUND);
//        }
//
//        return verificationMapper.deleteVerificationById(id) > 0;
//    }
//
//    @Override
//    public boolean isExistById(int id) {
//        return verificationMapper.isExistVerificationById(id);
    //   }
}
