package com.api.government.security.agency.repositories;

import com.api.government.security.agency.lib.entity.FileModel;
import com.api.government.security.agency.lib.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileModel, Long> {
    List<FileModel> findAllByUserModel(final UserModel user);
}
