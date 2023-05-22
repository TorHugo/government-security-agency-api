package com.api.government.security.agency.repositories;

import com.api.government.security.agency.lib.entity.ImageModel;
import com.api.government.security.agency.lib.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    List<ImageModel> findAllByUserModel(final UserModel user);
}
