package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity=CarData.class, fetch=FetchType.EAGER)
    private CarData carData;

    @OneToOne(targetEntity=Customer.class, fetch=FetchType.EAGER)
    private Customer customer;

    private Double millage;

    private int duration;

    private Double interestRate;

}
