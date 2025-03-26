package com.example.servicemanagementsystem.entity;

import com.example.servicemanagementsystem.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import com.example.servicemanagementsystem.enums.UserRole;
@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private int phone;
    private UserRole role;

    public UserDto getDto()
    {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setRole(role);

        return userDto;
    }
}
