package com.stonehnh.homestay.service.impl;

import com.stonehnh.common.dto.PageDTO;
import com.stonehnh.customer.service.CustomerService;
import com.stonehnh.homestay.converter.HomestayConverter;
import com.stonehnh.homestay.dto.request.CreationHomestayDto;
import com.stonehnh.homestay.dto.response.HomestayDetailResponseDto;
import com.stonehnh.homestay.dto.response.HomestayHomePageResponseDto;
import com.stonehnh.homestay.dto.response.HomestayResponseDto;
import com.stonehnh.homestay.entity.Homestay;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.homestay.mapper.HomestayMapper;
import com.stonehnh.homestay.service.HomestayImageService;
import com.stonehnh.homestay.service.HomestayRulesService;
import com.stonehnh.homestay.service.HomestayService;
import com.stonehnh.owner.service.OwnerService;
import com.stonehnh.review.service.RateService;
import com.stonehnh.review.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HomestayServiceImpl implements HomestayService {

    private final HomestayMapper homestayMapper;
    private final HomestayRulesService homestayRulesService;
    private final HomestayImageService homestayImageService;
    private final OwnerService ownerService;
    private final CustomerService customerService;
    private final ReviewService reviewService;
    private final RateService rateService;

    public HomestayServiceImpl(HomestayMapper homestayMapper,
                               HomestayRulesService homestayRulesService,
                               HomestayImageService homestayImageService,
                               OwnerService ownerService,
                               CustomerService customerService,
                               ReviewService reviewService,
                               RateService rateService) {
        this.homestayMapper = homestayMapper;
        this.homestayRulesService = homestayRulesService;
        this.homestayImageService = homestayImageService;
        this.ownerService = ownerService;
        this.customerService = customerService;
        this.reviewService = reviewService;
        this.rateService = rateService;
    }

    @Override
    public List<HomestayResponseDto> getAllHomestays() {
        List<Homestay> homestays = homestayMapper.findAllHomestays();
        return HomestayConverter.toDtoList(homestays);
    }

    @Override
    public HomestayResponseDto findHomestayById(String homestayId) {
        Homestay homestay = homestayMapper.findHomestayById(homestayId);
        if (homestay == null) {
            throw new AppException(ErrorCode.HOMESTAY_NOT_FOUND);
        }
        return HomestayConverter.toDto(homestay);
    }

    @Override
    public PageDTO<HomestayHomePageResponseDto> getHomestayHomePage(String category, int page, int size) {

        String checkCategory = (category == null || category.isEmpty() || category.equalsIgnoreCase("all")) ? null : category;

        int offset = (page - 1) * size;
        int totalItems = homestayMapper.countHomestayHomePage(checkCategory);
        int totalPages = (int) Math.ceil((double) totalItems / size);

        List<HomestayHomePageResponseDto> data = homestayMapper.selectHomestayHomePage(checkCategory , size, offset);
        return new PageDTO<>(page, size, totalItems, totalPages, data);
    }

    @Override
    public HomestayDetailResponseDto getHomestayDetail(String homestayId) {
        HomestayDetailResponseDto dto = new HomestayDetailResponseDto();
        dto.setHomestay(HomestayConverter.toDto(homestayMapper.findHomestayById(homestayId)));
        dto.setHomestayRule(homestayRulesService.getRulesByHomestayId(homestayId));
        dto.setImages(homestayImageService.getImagesByHomestayId(homestayId));
        dto.setOwner(ownerService.getOwnersByHomestayId(homestayId));
        dto.setCustomer(customerService.findCustomerByCustomerId(dto.getOwner().getCustomerId()));
        dto.setReviews(reviewService.findReviewsByHomestayId(homestayId));
        dto.setRates(rateService.findRateByHomestayId(homestayId));
        return dto;
    }


    @Override
    public int createHomestay(CreationHomestayDto homestayDto) {
        Homestay homestay = HomestayConverter.toEntity(homestayDto);
        homestay.setHomestayId(UUID.randomUUID().toString());
        return homestayMapper.insertHomestay(homestay);
    }

    @Override
    public int updateHomestay(String homestayId, CreationHomestayDto homestayDto) {
        boolean exists = homestayMapper.isExistedHomestayById(homestayId);
        if (!exists) {
            throw new AppException(ErrorCode.HOMESTAY_NOT_FOUND);
        }

        Homestay homestay = HomestayConverter.toEntity(homestayDto);
        homestay.setHomestayId(homestayId);
        return homestayMapper.updateHomestay(homestay);
    }

    @Override
    public int deleteHomestay(String homestayId) {
        boolean exists = homestayMapper.isExistedHomestayById(homestayId);
        if (!exists) {
            throw new AppException(ErrorCode.HOMESTAY_NOT_FOUND);
        }

        return homestayMapper.deleteHomestayById(homestayId);
    }
}