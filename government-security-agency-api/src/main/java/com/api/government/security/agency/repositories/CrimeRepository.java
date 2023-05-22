package com.api.government.security.agency.repositories;

import com.api.government.security.agency.lib.entity.CrimeModel;
import com.api.government.security.agency.lib.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrimeRepository extends JpaRepository<CrimeModel, Long> {
    List<CrimeModel> findAllByUserModel(final UserModel user);
}
