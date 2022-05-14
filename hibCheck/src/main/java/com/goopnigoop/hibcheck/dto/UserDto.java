package com.goopnigoop.hibcheck.dto;

import com.goopnigoop.hibcheck.entities.User;
import com.goopnigoop.hibcheck.entities.UserAccount;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {
    Integer id;
    String name;
    String surname;
    LocalDate birthdate;

    Set<UserAccount> userAccounts;

    public UserDto(Integer id, String name, String surname, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public UserDto(User userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.surname = userEntity.getSurname();
        this.birthdate = userEntity.getBirthdate();
    }

    public User toUserAccount() {
        return new User(this.id,this.name,this.surname,this.birthdate);
    }
}
