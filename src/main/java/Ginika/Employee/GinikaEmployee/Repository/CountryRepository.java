package Ginika.Employee.GinikaEmployee.Repository;

import Ginika.Employee.GinikaEmployee.Entity.Country;
import Ginika.Employee.GinikaEmployee.Entity.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Designation findByCountry (String country);
}
