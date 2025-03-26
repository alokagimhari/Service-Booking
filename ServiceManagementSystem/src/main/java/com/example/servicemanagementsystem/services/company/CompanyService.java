package com.example.servicemanagementsystem.services.company;

import com.example.servicemanagementsystem.dto.AdDto;

import java.io.IOException;
import java.util.List;

public interface CompanyService {
    boolean postAd(Long userId, AdDto adDto) throws IOException;
    List<AdDto> getAllAds(Long userId);
}
