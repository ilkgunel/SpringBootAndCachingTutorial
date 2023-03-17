package com.ilkaygunel.api;

import com.ilkaygunel.entities.Driver;
import com.ilkaygunel.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping("/driver")
public class DriverController {

    Logger logger = LoggerFactory.getLogger(DriverController.class);

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/driver/{driverId}")
    public ResponseEntity<Driver> getDriverById(@PathVariable(value = "driverId") Long driverId) {
        Instant start = Instant.now();

        Driver driver = driverService.getDriverById(driverId);

        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toMillis();
        logger.info("Elapsed Time in getDriverById: {} ms", time);

        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    @PutMapping(value = "/{driverId}")
    public ResponseEntity<HttpStatus> updateDriverById(@PathVariable(value = "driverId") Long driverId, @RequestBody Driver driver) {
        Instant start = Instant.now();

        driverService.updateDriverById(driverId, driver);

        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toMillis();
        logger.info("Elapsed Time in updateDriverById: {} ms", time);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{driverId}")
    public ResponseEntity<HttpStatus> deleteDriverById(@PathVariable(value = "driverId") Long driverId) {
        driverService.deleteDriverById(driverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
