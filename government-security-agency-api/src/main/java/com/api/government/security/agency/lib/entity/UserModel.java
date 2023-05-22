package com.api.government.security.agency.lib.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
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

    @OneToMany(mappedBy = "userModel", fetch = FetchType.LAZY)
    private List<FileModel> files = new ArrayList<>();

    @OneToMany(mappedBy = "userModel", fetch = FetchType.LAZY)
    private List<ImageModel> images = new ArrayList<>();

    @OneToMany(mappedBy = "userModel", fetch = FetchType.LAZY)
    private List<LanguageModel> languages = new ArrayList<>();

    @OneToMany(mappedBy = "userModel", fetch = FetchType.LAZY)
    private List<AliasesModel> aliases = new ArrayList<>();

    @OneToMany(mappedBy = "userModel", fetch = FetchType.LAZY)
    private List<CrimeModel> crimes = new ArrayList<>();

    public UserModel(Long id, String uid, String dateOfPublication, String reward, String descriptionSuspect, String titlePublication, String criminalClassification) {
        this.id = id;
        this.uid = uid;
        this.dateOfPublication = dateOfPublication;
        this.reward = reward;
        this.descriptionSuspect = descriptionSuspect;
        this.titlePublication = titlePublication;
        this.criminalClassification = criminalClassification;
    }

    public UserModel(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(String dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getDescriptionSuspect() {
        return descriptionSuspect;
    }

    public void setDescriptionSuspect(String descriptionSuspect) {
        this.descriptionSuspect = descriptionSuspect;
    }

    public String getTitlePublication() {
        return titlePublication;
    }

    public void setTitlePublication(String titlePublication) {
        this.titlePublication = titlePublication;
    }

    public String getCriminalClassification() {
        return criminalClassification;
    }

    public void setCriminalClassification(String criminalClassification) {
        this.criminalClassification = criminalClassification;
    }

    public CharacteristicModel getCharacteristicModel() {
        return characteristicModel;
    }

    public void setCharacteristicModel(CharacteristicModel characteristicModel) {
        this.characteristicModel = characteristicModel;
    }

    public List<FileModel> getFiles() {
        return files;
    }

    public void setFiles(List<FileModel> files) {
        this.files = files;
    }

    public List<ImageModel> getImages() {
        return images;
    }

    public void setImages(List<ImageModel> images) {
        this.images = images;
    }

    public List<LanguageModel> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageModel> languages) {
        this.languages = languages;
    }

    public List<AliasesModel> getAliases() {
        return aliases;
    }

    public void setAliases(List<AliasesModel> aliases) {
        this.aliases = aliases;
    }

    public List<CrimeModel> getCrimes() {
        return crimes;
    }

    public void setCrimes(List<CrimeModel> crimes) {
        this.crimes = crimes;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", dateOfPublication='" + dateOfPublication + '\'' +
                ", reward='" + reward + '\'' +
                ", descriptionSuspect='" + descriptionSuspect + '\'' +
                ", titlePublication='" + titlePublication + '\'' +
                ", criminalClassification='" + criminalClassification + '\'' +
                '}';
    }
}
