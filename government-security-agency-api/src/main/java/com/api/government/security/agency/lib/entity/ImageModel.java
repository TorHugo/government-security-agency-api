package com.api.government.security.agency.lib.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = "tb_image")
@Data
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @Column(name = "ds_uri_image")
    private String uriImage;

    @Column(name = "ds_caption")
    @Lob
    private String caption;
}
