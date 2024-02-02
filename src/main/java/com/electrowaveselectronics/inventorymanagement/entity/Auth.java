package com.electrowaveselectronics.inventorymanagement.entity;

import com.electrowaveselectronics.inventorymanagement.util.EnumRole;
import jakarta.persistence.*;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Data
@Entity
@Table(name = "auth")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

//    @Column(name = "password")
//    private String password;

    @Column(name = "cookie", unique = true, length = 500)
    private Cookie cookie;

    public Auth(String username, Cookie cookie) {
        this.username = username;
        this.cookie = cookie;
    }
}

