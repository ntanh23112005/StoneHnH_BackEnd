package com.stonehnh.homestay.controller;

import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.homestay.dto.request.CreationHomestayRulesDto;
import com.stonehnh.homestay.service.HomestayRulesService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/homestay-rule")
public class HomestayRuleController {

    private final HomestayRulesService homestayRulesService;

    public HomestayRuleController(HomestayRulesService homestayRulesService) {
        this.homestayRulesService = homestayRulesService;
    }

    /**
     * API lấy tất cả các quy tắc
     *
     * @return Danh sách các quy tắc
     */
//    @GetMapping
//    public ApiResponse<Object> getAllHomestayRules() {
//        return ApiResponse.builder()
//                .success(true)
//                .message("Lấy danh sách quy tắc thành công")
//                .data(homestayRulesService.getAllRules())
//                .build();
//    }

    /**
     * API tạo một quy tắc mới
     *
     * @return Thông tin quy tắc đã tạo
     */
    @PostMapping
    public ApiResponse<Object> createNewHomestayRule(
            @RequestBody CreationHomestayRulesDto creationHomestayRulesDto
    ) {
        return ApiResponse.builder()
                .success(true)
                .message("Tạo quy tắc thành công")
                .data(homestayRulesService.createRule(creationHomestayRulesDto))
                .build();
    }

    /**
     * API cập nhật một quy tắc hiện có
     *
     * @return Thông tin quy tắc đã cập nhật
     */
    @PutMapping("/{id}")
    public ApiResponse<Object> updateHomestayRule(
            @PathVariable("id") int id,
            @RequestBody CreationHomestayRulesDto creationHomestayRulesDto
    ) {
        return ApiResponse.builder()
                .success(true)
                .message("Cập nhật quy tắc thành công")
                .data(homestayRulesService.updateRule(id, creationHomestayRulesDto))
                .build();
    }

    /**
     * API xóa một quy tắc
     *
     * @return Kết quả xóa
     */
    @DeleteMapping("/{ruleId}")
    public ApiResponse<Object> deleteHomestayRule(String ruleId) {
        return ApiResponse.builder()
                .success(true)
                .message("Xóa quy tắc thành công")
                .data(homestayRulesService.deleteRule(Integer.parseInt(ruleId)))
                .build();
    }

    /**
     * API lấy quy tắc theo ID
     *
     * @return Thông tin quy tắc
     */
    @GetMapping("/{ruleId}")
    public ApiResponse<Object> getHomestayRuleById(@PathVariable("ruleId") String ruleId) {
        return ApiResponse.builder()
                .success(true)
                .message("Lấy quy tắc theo ID thành công")
                .data(homestayRulesService.findRuleById(Integer.parseInt(ruleId)))
                .build();
    }

    @GetMapping("/by-homestay/{homestayId}")
    public ApiResponse<Object> getHomestayRuleByHomestayId(@PathVariable("homestayId") String homestayId) {
        return ApiResponse.builder()
                .success(true)
                .message("Lấy quy tắc theo homestayId thành công")
                .data(homestayRulesService.findRuleByHomestayId(homestayId))
                .build();
    }

}
