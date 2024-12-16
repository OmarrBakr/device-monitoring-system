package com.example.task3.repositories;

import com.example.task3.entities.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepo extends JpaRepository<Alert,Long> {
}
