package Ginika.Employee.GinikaEmployee.Service;

import Ginika.Employee.GinikaEmployee.Dto.AppResponse;
import Ginika.Employee.GinikaEmployee.Dto.AuthenticationRequest;
import Ginika.Employee.GinikaEmployee.Dto.EmployeeDto;
import Ginika.Employee.GinikaEmployee.Entity.*;
import Ginika.Employee.GinikaEmployee.Exception.ApiException;
import Ginika.Employee.GinikaEmployee.Repository.CountryRepository;
import Ginika.Employee.GinikaEmployee.Repository.DesignationRepository;
import Ginika.Employee.GinikaEmployee.Repository.EmployeeRepository;
import Ginika.Employee.GinikaEmployee.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final EmployeeRepository employeeRepository;

    private final DesignationRepository designationRepository;
    private final RoleRepository roleRepository;

    private final MyUserDetailsService myUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    //private final CountryRepository countryRepository;



    public AppResponse<String> create(EmployeeDto employeedto){
        boolean check = employeeRepository.existsByUsernameOrEmail(employeedto.getUsername(), employeedto.getEmail());

        if (check) throw new ApiException("User already exists, login to continue");

        Employee addingEmployee = new Employee();
        addingEmployee.setEmployeeName(employeedto.getEmployeeName());
        addingEmployee.setDepartment(employeedto.getDepartment());
        addingEmployee.setDateOfBirth(employeedto.getDateOfBirth());
        addingEmployee.setDateOfJoining(employeedto.getDateOfJoining());
        addingEmployee.setMaritalStatus(employeedto.getMaritalStatus());
        addingEmployee.setDateOfMarriage(employeedto.getDateOfMarriage());
        addingEmployee.setUsername(employeedto.getUsername());
        addingEmployee.setPassword(employeedto.getPassword());
        addingEmployee.setEmail(employeedto.getEmail());

        Country country = new Country();
        country.setCountry(employeedto.getCountry());
        addingEmployee.setCountry(country);
        addingEmployee.setDesignation(returnDesignation(employeedto));
        addingEmployee.setPassword(passwordEncoder.encode(employeedto.getPassword()));

        addingEmployee.setTasks(returnTasks(employeedto, addingEmployee) );

        Role roles = roleRepository.findByName(employeedto.getRole());
        if (roles == null){
            Role role = new Role();
            role.setName(employeedto.getRole());
            addingEmployee.setRoles(role);
        }else {
            addingEmployee.setRoles(roles);
        }

        employeeRepository.save(addingEmployee);

        return new AppResponse<>(0, "successful");
    }


    public AppResponse<String> login(AuthenticationRequest authenticationRequest) {

        var user = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword()))
            return  new AppResponse<>(0,"wrong gmail/password");

        var jwtToken = jwtService.generateToken(user);


        return  new AppResponse<>(0,"Successfully logged in", jwtToken);

    }


    public Designation returnDesignation(EmployeeDto emp){
        Designation designationDB =designationRepository.findByDesignation(emp.getDesignation());

        if (designationDB == null){
            Designation designation = new Designation();
            designation.setDesignation(emp.getDesignation());
            return designation;
        }

        return designationDB;
    }


    public List<Tasks> returnTasks(EmployeeDto employeedto, Employee employee){
        List<String> tkS = employeedto.getTasks();
        List<Tasks> tasksList = new ArrayList<>();

        for (String b : tkS) {
            Tasks tasks = new Tasks();
            tasks.setTasks(b);
            tasks.setEmployee(employee);

            tasksList.add(tasks);
        }

        return tasksList;
    }

}
