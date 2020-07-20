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
public class Customer {

    @Id
    private String cid;

    @NonNull
    private String name;

    @NonNull
    private String street;

    @NonNull
    private String houseNumber;

    @NonNull
    private String zipCode;

    @NonNull
    private String place;

    @NonNull
    private String email;

    @NonNull
    private String phoneNumber;
}
