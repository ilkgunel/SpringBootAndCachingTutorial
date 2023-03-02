package com.ilkaygunel.service;

import com.ilkaygunel.entities.Driver;
import com.ilkaygunel.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver getDriverById(Long id) {
        Instant start = Instant.now();
        try {
            return driverRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find any driver with that id!"));
        } finally {
            Instant finish = Instant.now();
            long time = Duration.between(start, finish).toMillis();
            System.out.println("Taking Time " + time + " ms");
        }
    }

    public String getDriverByName(String name) {
        Optional<Driver> optionalDriver = driverRepository.findByName(name);
        if (optionalDriver.isPresent()) {
            return optionalDriver.get().getName();
        } else {
            return "NOT_FOUND";
        }
    }

}
