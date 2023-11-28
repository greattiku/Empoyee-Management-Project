package Ginika.Employee.GinikaEmployee.Repository;

import Ginika.Employee.GinikaEmployee.Entity.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long> {
    Designation findByDesignation (String designation);
}
