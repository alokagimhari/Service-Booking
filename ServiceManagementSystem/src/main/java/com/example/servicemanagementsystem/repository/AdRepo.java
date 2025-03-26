package com.example.servicemanagementsystem.repository;

import com.example.servicemanagementsystem.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepo extends JpaRepository<Ad,Long> {

    List<Ad> findAllByUserId(Long userId);
}
