package com.example.task3.repositories;

import com.example.task3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query("""
        SELECT u FROM User u WHERE u.username = :username
    """)
    Optional<User> findByUsername(@Param("username") String username);

    @Query("""
        SELECT u FROM User u WHERE u.email = :email
    """)
    Optional<User> findByEmail(@Param("email") String email);

    @Override
    @Query("SELECT u FROM User u")
    List<User> findAll();

    @Override
    @Query("SELECT u FROM User u WHERE u.id = ?1")
    Optional<User> findById(Long id);

    @Query("""
            SELECT u FROM User u WHERE u.device.id = :subscribedTo
        """)
    List<User> findBySubscribedTo(Long subscribedTo);


}