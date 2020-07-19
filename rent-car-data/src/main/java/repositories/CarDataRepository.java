package repositories;


import domain.CarData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarDataRepository extends JpaRepository<CarData,String> {
    Optional<CarData> findByLicensePlate(String licensePlate);
}
