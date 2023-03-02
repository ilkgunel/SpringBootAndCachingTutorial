package com.ilkaygunel.repository;

import com.ilkaygunel.entities.Driver;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByName(String name);
    @Cacheable("getDriverById")
    Optional<Driver> findById(Long id);
}
