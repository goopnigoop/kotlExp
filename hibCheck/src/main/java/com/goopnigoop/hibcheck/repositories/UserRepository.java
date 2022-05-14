package com.goopnigoop.hibcheck.repositories;

import com.goopnigoop.hibcheck.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
