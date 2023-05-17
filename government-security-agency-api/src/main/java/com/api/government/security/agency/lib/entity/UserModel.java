package com.api.government.security.agency.lib.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Data
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "ds_uid")
    private String uid;

    @Column(name = "dt_publication")
    private String dateOfPublication;

    @Column(name = "ds_reward")
    @Lob
    private String reward;

    @Column(name = "ds_suspect")
    @Lob
    private String descriptionSuspect;

    @Column(name = "nm_title_publication")
    private String titlePublication;

    @Column(name = "nm_criminal_classification")
    private String criminalClassification;

    @OneToOne(mappedBy = "userModel")
    private CharacteristicModel characteristicModel;

    @OneToMany(mappedBy = "userModel")
    private List<FileModel> files = new ArrayList<>();

    @OneToMany(mappedBy = "userModel")
    private List<ImageModel> images = new ArrayList<>();

    @OneToMany(mappedBy = "userModel")
    private List<LanguageModel> languages = new ArrayList<>();

    @OneToMany(mappedBy = "userModel")
    private List<AliasesModel> aliases = new ArrayList<>();

    @OneToMany(mappedBy = "userModel")
    private List<CrimeModel> crimes = new ArrayList<>();
}
