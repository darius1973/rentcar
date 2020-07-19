package com.car.data.api;

import domain.CarData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public  class CarDataResource {

    @Autowired
    private CarDataService carDataService;

    @GetMapping("/cardata/{licensePlate}")
    public CarData retrieveCarData(@PathVariable String  licensePlate) {
        return carDataService.retrieveCarData(licensePlate);
    }


    @DeleteMapping("/cardata/{licensePlate}")
    public ResponseEntity<HttpStatus> deleteCarData(@PathVariable String licensePlate) {
        carDataService.deleteCarData(licensePlate);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/cardata")
    public ResponseEntity<CarData> createCarData(@Valid @RequestBody CarData carData) {
        CarData savedCarData = carDataService.persist(carData);
        return new ResponseEntity(carData, HttpStatus.CREATED);
    }
    @PutMapping(value = "/cardata/{licensePlate}")
    public ResponseEntity<CarData> updateCarData(@PathVariable String licensePlate, @Valid @RequestBody CarData carData) {
        String updatedCarLicensePlate = carDataService.updateCarData(carData, licensePlate);
        if(StringUtils.isEmpty(updatedCarLicensePlate)) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(carData, HttpStatus.OK);
        }
    }
}
