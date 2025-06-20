package com.stonehnh.homestay.controller;

import com.stonehnh.common.dto.PageDTO;
import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.homestay.dto.request.CreationHomestayDto;
import com.stonehnh.homestay.dto.response.HomestayHomePageResponseDto;
import com.stonehnh.homestay.service.HomestayService;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/homestay")
public class HomeStayController {

    private final HomestayService homestayService;

    public HomeStayController(HomestayService homestayService){this.homestayService = homestayService;}

    /**
     * API lấy hết homestay
     *
     * @return List HomeStay
     * */
//    @GetMapping
//    public ApiResponse<Object> getAllHomestays(){
//        return ApiResponse.builder()
//                .success(true)
//                .message("Lấy danh sách homestay thành công")
//                .data(homestayService.getAllHomestays())
//                .build();
//    }

    @PostMapping
    public ApiResponse<Object> createNewHomeStay(CreationHomestayDto creationHomestayDto){
        return ApiResponse.builder()
                .success(true)
                .message("Tạo 1 homestay thành công")
                .data(homestayService.createHomestay(creationHomestayDto))
                .build();
    }

    @PutMapping
    public ApiResponse<Object> updateHomestay(String homestayId, CreationHomestayDto creationHomestayDto){
        return ApiResponse.builder()
                .success(true)
                .message("Update 1 homestay thành công")
                .data(homestayService.updateHomestay(homestayId, creationHomestayDto))
                .build();
    }

    @DeleteMapping
    public ApiResponse<Object> deleteHomestay(String homestayId){
        return ApiResponse.builder()
                .success(true)
                .message("Delete 1 homestay thành công")
                .data(homestayService.deleteHomestay(homestayId))
                .build();
    }

    @GetMapping("/{homestayId}")
    public ApiResponse<Object> getHomestayById(@PathVariable String homestayId){
        return ApiResponse.builder()
                .success(true)
                .message("Get homestay theo id thành công")
                .data(homestayService.getHomestayDetail(homestayId))
                .build();
    }

    /**
     * API lấy danh sách homestay cho home page có phân trang
     *
     * @param page trang hiện tại (mặc định là 1)
     * @param size số lượng homestay mỗi trang (cố định là 8)
     * @return List Homestay + pagination metadata
     */
    @GetMapping
    public ApiResponse<Object> getAllHomestaysForHomePage(
            @RequestParam String category,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size) {

        PageDTO<HomestayHomePageResponseDto> result = homestayService.getHomestayHomePage(category, page, size);

        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách homestay thành công")
                .data(result)
                .build();
    }
}
