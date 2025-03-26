package com.example.servicemanagementsystem.repository;

import com.example.servicemanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
     User findFirstByEmail(String email);
}
