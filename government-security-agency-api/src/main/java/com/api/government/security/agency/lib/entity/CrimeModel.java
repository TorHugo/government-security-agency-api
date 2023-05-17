package com.api.government.security.agency.lib.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = "tb_crime")
@Data
public class CrimeModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crime_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @Column(name = "nm_crime")
    @Lob
    private String nmCrime;
}
