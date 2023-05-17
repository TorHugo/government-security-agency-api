package com.api.government.security.agency.lib.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_aliase")
@Data
public class AliasesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aliase")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @Column(name = "nm_aliase")
    private String nmAliase;
}
