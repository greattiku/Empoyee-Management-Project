package Ginika.Employee.GinikaEmployee.Repository;

import Ginika.Employee.GinikaEmployee.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);

}