package com.stonehnh.owner.service.impl;

import com.stonehnh.owner.converter.OwnerConverter;
import com.stonehnh.owner.dto.request.CreationOwnerDto;
import com.stonehnh.owner.dto.response.OwnerResponseDto;
import com.stonehnh.owner.entity.Owner;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.owner.mapper.OwnerMapper;
import com.stonehnh.owner.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerMapper ownerMapper;

    public OwnerServiceImpl(OwnerMapper ownerMapper) {
        this.ownerMapper = ownerMapper;
    }

    @Override
    public List<OwnerResponseDto> getOwnersByHomestayId(String homestayId) {
        List<Owner> owners = ownerMapper.findOwnersByHomestayId(homestayId);
        return OwnerConverter.toDtoList(owners);
    }

    @Override
    public int createOwner(CreationOwnerDto dto) {
        Owner owner = OwnerConverter.toEntity(dto);
        return ownerMapper.insertOwner(owner);
    }

    @Override
    public int updateOwnership(String customerId, CreationOwnerDto dto) {
        boolean exists = ownerMapper.existsOwner(customerId);
        if (!exists) {
            throw new AppException(ErrorCode.OWNER_NOT_FOUND);
        }
        Owner owner = OwnerConverter.toEntity(dto);
        owner.setCustomerId(customerId);
        return ownerMapper.updateOwner(owner);
    }

    @Override
    public int deleteOwner(String customerId, String homestayId) {
        boolean exists = ownerMapper.existsOwner(customerId);
        if (!exists) {
            throw new AppException(ErrorCode.OWNER_NOT_FOUND);
        }
        return ownerMapper.deleteOwner(customerId, homestayId);
    }
}