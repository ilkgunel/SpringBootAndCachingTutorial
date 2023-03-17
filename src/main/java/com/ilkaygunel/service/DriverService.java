package com.ilkaygunel.service;

import com.ilkaygunel.entities.Driver;
import com.ilkaygunel.repository.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService {

    Logger logger = LoggerFactory.getLogger(DriverService.class);

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Cacheable(value = "drivers", key = "#driverId")
    public Driver getDriverById(Long driverId) {
        return driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Couldn't find any driver with that driverId!"));
    }

    @CachePut(value = "drivers", key = "#driverId")
    public Driver updateDriverById(Long driverId, Driver incomingDriver) {
        Driver existingDriver = driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Couldn't find any driver with that driverId!"));
        existingDriver.setName(incomingDriver.getName());
        existingDriver.setSurname(incomingDriver.getSurname());
        driverRepository.save(existingDriver);
        return existingDriver;
    }

    @CacheEvict(value = "drivers", key = "#driverId")
    public void deleteDriverById(Long driverId) {
        driverRepository.deleteById(driverId);
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
