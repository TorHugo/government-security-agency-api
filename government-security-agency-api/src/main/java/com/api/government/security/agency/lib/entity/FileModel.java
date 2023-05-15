package com.api.government.security.agency.lib.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_file")
@Data
public class FileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @Column(name = "ds_file_uri")
    private String fileUri;
    @Column(name = "nm_file")
    private String nmFile;
}
