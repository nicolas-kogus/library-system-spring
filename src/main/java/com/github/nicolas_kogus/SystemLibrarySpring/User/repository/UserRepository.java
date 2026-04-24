package com.github.nicolas_kogus.SystemLibrarySpring.User.repository;

import com.github.nicolas_kogus.SystemLibrarySpring.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    String findByName(String name);
    Boolean existsByName(String name);
}
