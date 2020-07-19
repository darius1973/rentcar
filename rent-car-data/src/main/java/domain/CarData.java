package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class CarData {

    @Id
    private String licensePlate;

    private String make;

    private String model;

    private String version;

    private int nbrOfDoors;

    private Double co2Emission;

    private Double grossPrice;

    private Double netPrice;

}
