package com.example.servicemanagementsystem.services.company;

import com.example.servicemanagementsystem.dto.AdDto;
import com.example.servicemanagementsystem.entity.Ad;
import com.example.servicemanagementsystem.entity.User;
import com.example.servicemanagementsystem.repository.AdRepo;
import com.example.servicemanagementsystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private AdRepo adRepo;

    @Autowired
    private UserRepo userRepo;

    public boolean postAd(Long userId, AdDto adDto) throws IOException {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()){
            Ad ad = new Ad();
            ad.setServiceName(adDto.getServiceName());
            ad.setDescription(adDto.getDescription());
            ad.setImg(adDto.getImg().getBytes());
            ad.setPrice(adDto.getPrice());
            ad.setUser(optionalUser.get());

            adRepo.save(ad);
            return true;
        }
        return false;
    }


    public List<AdDto> getAllAds(Long userId)
    {
        return adRepo.findAllByUserId(userId).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }
}
