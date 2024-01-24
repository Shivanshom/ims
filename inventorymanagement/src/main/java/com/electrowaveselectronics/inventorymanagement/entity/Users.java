package com.electrowaveselectronics.inventorymanagement.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private EnumRole role;

//    @Column(name = "godown_id")
//    private int godownId;

    public Users(int userId, String userName, String password, EnumRole role) {
        this.userId=userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
//        this.godownId = godownId;
    }

    public Users() {
    }
}