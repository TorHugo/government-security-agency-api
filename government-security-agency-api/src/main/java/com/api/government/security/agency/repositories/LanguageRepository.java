package com.api.government.security.agency.repositories;

import com.api.government.security.agency.lib.entity.LanguageModel;
import com.api.government.security.agency.lib.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageModel, Long> {
}
