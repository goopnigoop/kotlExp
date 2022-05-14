package com.goopnigoop.hibcheck.entities;

import com.goopnigoop.hibcheck.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class User {

    public User(UserDto userDto) {
        this.id = userDto.getId();
        this.name = userDto.getName();
        this.surname = userDto.getSurname();
        this.birthdate = userDto.getBirthdate();
    }

    public User(String name, String surname, LocalDate birthdate) {
        this.surname = surname;
        this.name = name;
        this.birthdate = birthdate;
    }

    public User(Integer id,String name, String surname, LocalDate birthdate) {
        this(name, surname, birthdate);
        this.id = id;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String surname;
    LocalDate birthdate;
    @OneToMany(mappedBy = "user")
    Set<UserAccount> userAccounts;
}
