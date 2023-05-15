package com.api.government.security.agency.lib.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_characteristic")
@Data
public class CharacteristicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "characteristic_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @Column(name = "nm_sex")
    private String nmSex;
    @Column(name = "tp_hair")
    private String typeHair;
    @Column(name = "nm_weight")
    private String weight;
    @Column(name = "nm_height")
    private String height;
    @Column(name = "nm_ethnicity")
    private String ethnicity;
    @Column(name = "nm_color_eye")
    private String colorEye;
    @Column(name = "nm_nationality")
    private String nationality;
    @Column(name = "nm_birth_place")
    private String birthPlace;
    @Column(name = "nm_age")
    private String age;
}
