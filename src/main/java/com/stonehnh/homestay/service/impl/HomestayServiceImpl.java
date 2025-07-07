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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<HomestayResponseDto> getAllHomestays() {
        try {
            List<Homestay> homestays = homestayMapper.findAllHomestays();
            return HomestayConverter.toDtoList(homestays);
        } catch (Exception e) {
            e.printStackTrace(); // Hoặc dùng log.error("Lỗi khi lấy danh sách homestay", e);
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public HomestayResponseDto findHomestayById(String homestayId) {
        try {
            Homestay homestay = homestayMapper.findHomestayById(homestayId);
            if (homestay == null) {
                throw new AppException(ErrorCode.HOMESTAY_NOT_FOUND);
            }

            return HomestayConverter.toDto(homestay);

        } catch (AppException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace(); // hoặc log.error(...) nếu có logging
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }


    @Override
    public PageDTO<HomestayHomePageResponseDto> getHomestayHomePage(String category, int page, int size) {

        String checkCategory = (category == null || category.isEmpty() || category.equalsIgnoreCase("all")) ? null : category;

        int offset = (page - 1) * size;
        int totalItems = homestayMapper.countHomestayHomePage(checkCategory);
        int totalPages = (int) Math.ceil((double) totalItems / size);

        List<HomestayHomePageResponseDto> data = homestayMapper.selectHomestayHomePage(checkCategory, size, offset);
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
    @Transactional(rollbackFor = Exception.class)
    public int createHomestay(CreationHomestayDto homestayDto) {
        try {
            Homestay homestay = HomestayConverter.toEntity(homestayDto);
            homestay.setHomestayId(UUID.randomUUID().toString());

            int rowsAffected = homestayMapper.insertHomestay(homestay);
            if (rowsAffected == 0) {
                throw new AppException(ErrorCode.HOMESTAY_CREATE_FAILED);
            }

            return rowsAffected;

        } catch (AppException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace(); // hoặc log.error("Lỗi tạo homestay", e);
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateHomestay(String homestayId, CreationHomestayDto homestayDto) {
        try {
            boolean exists = homestayMapper.isExistedHomestayById(homestayId);
            if (!exists) {
                throw new AppException(ErrorCode.HOMESTAY_NOT_FOUND);
            }

            Homestay homestay = HomestayConverter.toEntity(homestayDto);
            homestay.setHomestayId(homestayId);

            int rowsAffected = homestayMapper.updateHomestay(homestay);
            if (rowsAffected == 0) {
                throw new AppException(ErrorCode.HOMESTAY_UPDATE_FAILED); // Gợi ý thêm nếu chưa có
            }

            return rowsAffected;

        } catch (AppException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace(); // hoặc log.error("Lỗi cập nhật homestay", e);
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteHomestay(String homestayId) {
        try {
            boolean exists = homestayMapper.isExistedHomestayById(homestayId);
            if (!exists) {
                throw new AppException(ErrorCode.HOMESTAY_NOT_FOUND);
            }

            int rowsAffected = homestayMapper.deleteHomestayById(homestayId);
            if (rowsAffected == 0) {
                throw new AppException(ErrorCode.HOMESTAY_DELETE_FAILED); // Gợi ý thêm mã lỗi này nếu chưa có
            }

            return rowsAffected;

        } catch (AppException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace(); // hoặc log.error("Lỗi khi xoá homestay", e);
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }
}