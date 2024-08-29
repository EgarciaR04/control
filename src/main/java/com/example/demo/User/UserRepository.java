package com.example.demo.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User_, Long> {
    Optional<User_> findByUsername(String username);

    @Query("SELECT u.id FROM User_ u WHERE u.username = :username")
    long findByUsername2(@Param("username") String username);

}
