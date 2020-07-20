package domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Contract {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NonNull
    private String licensePlate;

    @NonNull
    private String  cid;

    @NonNull
    private Double millage;

    @NonNull
    private int duration;

    @NonNull
    private Double interestRate;

    private Double leaseRate;
}
