package com.goopnigoop.hibcheck.services;

import com.goopnigoop.hibcheck.dto.UserDto;

import java.time.LocalDate;

public interface UserService {
    UserDto createUser(String userName, String surname, LocalDate birthDate);

    void clear();
}
