package com.stonehnh.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDTO<T> {
    private int currentPage;
    private int pageSize;
    private long totalItems;
    private int totalPages;
    private List<T> data;
}
