package domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class CarData {

    @Id
    private String licensePlate;

    @NonNull
    private String make;

    @NonNull
    private String model;

    @NonNull
    private String version;

    @NonNull
    private int nbrOfDoors;

    @NonNull
    private Double co2Emission;

    @NonNull
    private Double grossPrice;

    @NonNull
    private Double netPrice;

}
