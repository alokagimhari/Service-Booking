package com.example.servicemanagementsystem.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AdDto {

    private Long id;

    private String serviceName;

    private String description;

    private double price;

    private MultipartFile img;

    private byte[] returnedImg;

    private Long userId;

    private String companyName;

}
