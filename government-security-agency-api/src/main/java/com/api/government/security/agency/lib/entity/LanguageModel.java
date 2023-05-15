package com.api.government.security.agency.lib.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = "tb_language")
@Data
public class LanguageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @Column(name = "nm_language")
    private String nmLanguage;
}
