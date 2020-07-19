package domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    private String cid;

    private String name;

    private String street;

    private String houseNumber;

    private String zipCode;

    private String place;

    private String email;

    private String phoneNumber;
}
