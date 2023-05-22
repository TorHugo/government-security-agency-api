package com.api.government.security.agency.repositories;

import com.api.government.security.agency.lib.entity.AliasesModel;
import com.api.government.security.agency.lib.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AliasesRepository extends JpaRepository<AliasesModel, Long> {
    List<AliasesModel> findAllByUserModel(final UserModel userModel);
}
