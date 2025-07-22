package com.stonehnh.owner.service.impl;

import com.stonehnh.customer.mapper.CustomerRoleMapper;
import com.stonehnh.owner.converter.OwnerConverter;
import com.stonehnh.owner.dto.request.CreationOwnerDto;
import com.stonehnh.owner.dto.response.*;
import com.stonehnh.owner.entity.Owner;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.owner.mapper.OwnerMapper;
import com.stonehnh.owner.service.OwnerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerMapper ownerMapper;
    private final CustomerRoleMapper customerRoleMapper;

    public OwnerServiceImpl(OwnerMapper ownerMapper, CustomerRoleMapper customerRoleMapper) {
        this.ownerMapper = ownerMapper;
        this.customerRoleMapper = customerRoleMapper;
    }

    @Override
    public OwnerResponseDto getOwnersByHomestayId(String homestayId) {
        Owner owners = ownerMapper.findOwnersByHomestayId(homestayId);
        return OwnerConverter.toDto(owners);
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

    @Transactional
    @Override
    public void registerAsOwner(String customerId) {
        boolean exists = customerRoleMapper.existsByCustomerIdAndRoleId(customerId, "R02");
        if (exists) {
            throw new IllegalStateException("Người dùng đã là chủ nhà.");
        }

        customerRoleMapper.insertRoleForCustomer(customerId, "R02");
    }

    @Transactional
    @Override
    public OwnerStatisticsDto getStatistics(String customerId) {
        return ownerMapper.getOwnerStatistics(customerId);
    }

    @Transactional
    @Override
    public List<OwnerBookingDetailDto> getAllBookingDetails(String customerId) {
        return ownerMapper.getAllBookingDetailsByOwner(customerId);
    }

    @Override
    public List<OwnerBookingDto> getAllBookings(String customerId) {
        return ownerMapper.getAllBookingsByOwner(customerId);
    }

    @Override
    public List<MonthlyRevenueOwnerDto> getMonthlyRevenue(String customerId) {
        return ownerMapper.getMonthlyRevenue(customerId);
    }

    @Override
    public List<OwnerHomestayDto> getOwnedHomestaysWithImages(String customerId) {
        return ownerMapper.getOwnedHomestaysWithImages(customerId);
    }
}