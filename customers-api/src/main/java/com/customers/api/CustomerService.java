package com.car.data.api;

import com.car.data.api.exception.CarDataNotFoundException;
import domain.CarData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CarDataRepository;

import java.util.Optional;


@Service
public class CarDataService {

    @Autowired
    private CarDataRepository carDataRepository;


    public CarData retrieveCarData(String licensePlate) {
        Optional<CarData> carData = carDataRepository.findByLicensePlate(licensePlate);
        if(!carData.isPresent()) {
            throw new CarDataNotFoundException("licensePlate: " + licensePlate);
        }
        return carData.get();
    }

    public void deleteCarData(String licensePlate) {
        carDataRepository.deleteById(licensePlate);
    }

    public CarData persist(CarData carData) {
        return carDataRepository.save(carData);
    }

    public String  updateCarData(CarData carData, String licensePlate) {
        Optional<CarData> carDataOptional = carDataRepository.findById(licensePlate);
        if(!carDataOptional.isPresent()) {
            return null;
        } else {
            carData.setLicensePlate(licensePlate);
            carDataRepository.save(carData);
            return licensePlate;
        }
    }
}
