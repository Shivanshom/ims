package com.electrowaveselectronics.inventorymanagement.entity;

import jakarta.persistence.*;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Auth {
    private String username;
    private String password;
    private Cookie cookie;
}

