package com.ilkaygunel.api;

import com.ilkaygunel.entities.Driver;
import com.ilkaygunel.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/driver/{driverId}")
    public ResponseEntity<Driver> getDriverById(@PathVariable(value = "driverId") Long driverId) {
        Driver driver = driverService.getDriverById(driverId);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

}
