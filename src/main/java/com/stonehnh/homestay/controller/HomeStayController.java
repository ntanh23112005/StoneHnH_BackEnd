package com.stonehnh.homestay.controller;

import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.homestay.dto.request.CreationHomestayDto;
import com.stonehnh.homestay.service.HomestayService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/homestay")
public class HomeStayController {

    private final HomestayService homestayService;

    public HomeStayController(HomestayService homestayService){this.homestayService = homestayService;}

    @GetMapping
    public ApiResponse<Object> getAllHomestays(){
        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách homestay thành công")
                .data(homestayService.getAllHomestays())
                .build();
    }

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

    @GetMapping("/{id}")
    public ApiResponse<Object> getHomestayById(@PathVariable("id") String homestayId){
        return ApiResponse.builder()
                .success(true)
                .message("Get homestay theo id thành công")
                .data(homestayService.findHomestayById(homestayId))
                .build();
    }

}
