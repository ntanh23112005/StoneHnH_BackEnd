package com.stonehnh.homestay.service.impl;

import com.stonehnh.homestay.converter.HomestayImagesConverter;
import com.stonehnh.homestay.dto.request.CreationHomestayImagesDto;
import com.stonehnh.homestay.dto.response.HomestayImagesResponseDto;
import com.stonehnh.homestay.entity.HomestayImages;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.homestay.service.HomestayImageService;
import com.stonehnh.homestay.mapper.HomestayImagesMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomestayImagesServiceImpl implements HomestayImageService {

    private final HomestayImagesMapper homestayImagesMapper;

    // Đường dẫn gốc
    private static final String IMAGE_BASE_PATH = "src/main/resources/static/images/HomeStay";

    public HomestayImagesServiceImpl(HomestayImagesMapper homestayImagesMapper) {
        this.homestayImagesMapper = homestayImagesMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createImage(CreationHomestayImagesDto dto) {
        try {
            String folderName = dto.getHomestayName();
            Path folderPath = Paths.get(IMAGE_BASE_PATH, folderName);

            if (Files.notExists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            int rows = 0;

            // Xử lý ảnh đại diện
            if (dto.getRepresent() != null && !dto.getRepresent().isEmpty()) {
                List<String> filenames = new ArrayList<>();
                for (MultipartFile file : dto.getRepresent()) {
                    String filename = file.getOriginalFilename();
                    if (filename == null) continue;

                    Path filePath = folderPath.resolve(filename);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    filenames.add(filename);
                }

                HomestayImages entity = new HomestayImages();
                entity.setHomestayId(dto.getHomestayId());
                entity.setImageFor("represent");
                entity.setHomestayImage(String.join(", ", filenames));

                int inserted = homestayImagesMapper.insertImage(entity);
                if (inserted == 0) throw new AppException(ErrorCode.HOMESTAY_IMAGE_UPLOAD_FAILED);
                rows += inserted;
            }

            // Xử lý ảnh thư viện
            if (dto.getCatalog() != null && !dto.getCatalog().isEmpty()) {
                List<String> filenames = new ArrayList<>();
                for (MultipartFile file : dto.getCatalog()) {
                    String filename = file.getOriginalFilename();
                    if (filename == null) continue;

                    Path filePath = folderPath.resolve(filename);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    filenames.add(filename);
                }

                HomestayImages entity = new HomestayImages();
                entity.setHomestayId(dto.getHomestayId());
                entity.setImageFor("catalog");
                entity.setHomestayImage(String.join(", ", filenames));

                int inserted = homestayImagesMapper.insertImage(entity);
                if (inserted == 0) throw new AppException(ErrorCode.HOMESTAY_IMAGE_UPLOAD_FAILED);
                rows += inserted;
            }

            return rows;
        } catch (IOException e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.FILE_IO_ERROR);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateImage(int id, CreationHomestayImagesDto dto) {
        try {
            HomestayImages oldImage = homestayImagesMapper.findImageById(id);
            if (oldImage == null) {
                throw new AppException(ErrorCode.HOMESTAY_IMAGE_NOT_FOUND);
            }

            // Xóa file cũ nếu tồn tại
            Path oldFilePath = Paths.get("src/main/resources/static/", oldImage.getHomestayImage());
            if (Files.exists(oldFilePath)) {
                Files.delete(oldFilePath);
            }

            // Tạo tên thư mục
            String folderName = slugify(dto.getHomestayName());
            Path folderPath = Paths.get(IMAGE_BASE_PATH, folderName);

            // Tạo thư mục nếu chưa tồn tại
            if (Files.notExists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            // Tạo tên file mới
//            String filename = System.currentTimeMillis() + "_" + dto.getImageFile().getOriginalFilename();
//            Path filePath = folderPath.resolve(filename);
//
//            // Lưu file mới
//            Files.copy(dto.getImageFile().getInputStream(), filePath);

            // Update DB
            HomestayImages newEntity = new HomestayImages();
            newEntity.setId(id);
            newEntity.setHomestayId(dto.getHomestayId());
            newEntity.setHomestayImage("/images/HomeStay/" + folderName + "/");
            newEntity.setImageFor(dto.getImageFor());

            int rows = homestayImagesMapper.updateImage(newEntity);
            if (rows == 0) {
                throw new AppException(ErrorCode.HOMESTAY_IMAGE_UPLOAD_FAILED);
            }

            return rows;

        } catch (IOException e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.FILE_IO_ERROR);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteImage(int id) {
        try {
            HomestayImages oldImage = homestayImagesMapper.findImageById(id);
            if (oldImage == null) {
                throw new AppException(ErrorCode.HOMESTAY_IMAGE_NOT_FOUND);
            }

            // Xóa file vật lý
            Path oldFilePath = Paths.get("src/main/resources/static/", oldImage.getHomestayImage());
            if (Files.exists(oldFilePath)) {
                Files.delete(oldFilePath);
            }

            // Xóa DB
            int rows = homestayImagesMapper.deleteImageById(id);
            if (rows == 0) {
                throw new AppException(ErrorCode.HOMESTAY_IMAGE_NOT_FOUND);
            }

            return rows;

        } catch (IOException e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.FILE_IO_ERROR);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public List<HomestayImagesResponseDto> getImagesByHomestayId(String homestayId) {
        List<HomestayImages> images = homestayImagesMapper.findImagesByHomestayId(homestayId);
        return HomestayImagesConverter.toDtoList(images);
    }

    @Override
    public HomestayImagesResponseDto findImageById(int id) {
        HomestayImages image = homestayImagesMapper.findImageById(id);
        if (image == null) {
            throw new AppException(ErrorCode.HOMESTAY_IMAGE_NOT_FOUND);
        }
        return HomestayImagesConverter.toDto(image);
    }

    private String slugify(String input) {
        return input.trim().toLowerCase().replaceAll("[^a-z0-9]+", "-");
    }
}