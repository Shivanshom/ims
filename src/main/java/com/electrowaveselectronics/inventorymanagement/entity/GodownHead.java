package com.electrowaveselectronics.inventorymanagement.entity;

import com.electrowaveselectronics.inventorymanagement.util.EnumRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "godownHead")
public class GodownHead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "godownHead_id")
    private int godownHeadId;

    @Column(name = "godownHead_name")
    private String godownHeadName;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private EnumRole role;

    public GodownHead(String userName, String password, EnumRole role) {
        this.godownHeadName = userName;
        this.password = password;
        this.role = role;
    }
}


