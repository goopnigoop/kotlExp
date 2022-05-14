package com.goopnigoop.hibcheck.services;

import com.goopnigoop.hibcheck.dto.UserDto;
import com.goopnigoop.hibcheck.entities.User;
import com.goopnigoop.hibcheck.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(String userName, String surname, LocalDate birthDate) {
        final User toSave = new User(userName, surname, birthDate);
        final User saved = userRepository.save(toSave);
        return new UserDto(saved.getId(),saved.getName(),saved.getSurname(),saved.getBirthdate());
    }

    @Override
    @Transactional
    public void clear() {
        userRepository.deleteAll();
    }
}
