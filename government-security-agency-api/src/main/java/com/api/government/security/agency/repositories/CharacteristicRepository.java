package com.api.government.security.agency.repositories;

import com.api.government.security.agency.lib.entity.CharacteristicModel;
import com.api.government.security.agency.lib.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepository extends JpaRepository<CharacteristicModel, Long> {
    CharacteristicModel findByUserModel(final UserModel userModel);
}
