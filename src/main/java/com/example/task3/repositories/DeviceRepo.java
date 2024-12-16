package com.example.task3.repositories;

import com.example.task3.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepo extends JpaRepository<Device,Long> {
}
