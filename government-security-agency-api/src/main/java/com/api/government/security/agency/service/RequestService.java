package com.api.government.security.agency.service;

import com.api.government.security.agency.lib.entity.CharacteristicModel;
import com.api.government.security.agency.lib.entity.UserModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RequestService {
    Integer requestForApi();
    <T> void saveToDataBase(final List<T> items);
    <T> void saveToDataBase(final T item);
}
