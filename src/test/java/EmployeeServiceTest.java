
import Ginika.Employee.GinikaEmployee.Dto.AppResponse;
import Ginika.Employee.GinikaEmployee.Dto.EmployeeDto;
import Ginika.Employee.GinikaEmployee.Dto.EmployeeResponseFromDb;
import Ginika.Employee.GinikaEmployee.Entity.Country;
import Ginika.Employee.GinikaEmployee.Entity.Designation;
import Ginika.Employee.GinikaEmployee.Entity.Employee;
import Ginika.Employee.GinikaEmployee.Entity.Tasks;
import Ginika.Employee.GinikaEmployee.Repository.CountryRepository;
import Ginika.Employee.GinikaEmployee.Repository.DesignationRepository;
import Ginika.Employee.GinikaEmployee.Repository.EmployeeRepository;
import Ginika.Employee.GinikaEmployee.Service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private DesignationRepository designationRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void EmployeeService_Create_Employee(){
        Employee addingEmployee = new Employee();
        Tasks tasks = new Tasks();
        Country country = new Country();
        Designation designation = new Designation();

        EmployeeDto employeedto = EmployeeDto.builder()
                .employeeName("Aisha")
                .country("Country")
                .department("HR")
                .designation("Full-Time")
                .dateOfJoining("12-01-2009")
                .dateOfBirth("12-06-1989")
                .maritalStatus("Single")
                .dateOfMarriage("Not-Married")
                .tasks(List.of("Save", "Call", "Meetings"))
                .build();

        country.setCountry("Nigeria");
        designation.setDesignation("Full-Time");


        tasks.setTasks("Save");
        tasks.setEmployee(addingEmployee);

        addingEmployee.setEmployeeName("Aisha");
        addingEmployee.setCountry(country);
        addingEmployee.setDepartment("HR");
        addingEmployee.setDesignation(designation);
        addingEmployee.setDateOfJoining("12-01-2009");
        addingEmployee.setDateOfBirth("12-06-1989");
        addingEmployee.setMaritalStatus("Single");
        addingEmployee.setDateOfMarriage("Not-Married");
        addingEmployee.setDepartment("HR");
        addingEmployee.setTasks(List.of(tasks));

        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(addingEmployee);
        AppResponse<String> response = employeeService.create(employeedto);

        Assertions.assertThat(response).isNotNull();
    }

    @Test
    public void EmployeeService_GetAll_Customer(){
      Pageable pageReq = PageRequest.of(0, 3);
        Employee addingEmployee = new Employee();
        Tasks tasks = new Tasks();
        Country country = new Country();
        Designation designation = new Designation();

        country.setCountry("Nigeria");
        designation.setDesignation("Full-Time");


        tasks.setTasks("Save");
        tasks.setEmployee(addingEmployee);

        addingEmployee.setEmployeeName("Aisha");
        addingEmployee.setCountry(country);
        addingEmployee.setDepartment("HR");
        addingEmployee.setDesignation(designation);
        addingEmployee.setDateOfJoining("12-01-2009");
        addingEmployee.setDateOfBirth("12-06-1989");
        addingEmployee.setMaritalStatus("Single");
        addingEmployee.setDateOfMarriage("Not-Married");
        addingEmployee.setDepartment("HR");
        addingEmployee.setTasks(List.of(tasks));


        Page<Employee> cus = new PageImpl<>(List.of(addingEmployee), pageReq, 1);

        when(employeeRepository.findAll(Mockito.any(Pageable.class))).thenReturn(cus);
        AppResponse<Map<String, Object>> response = employeeService.getAll(pageReq);
        Assertions.assertThat(response).isNotNull();
    }

    @Test
    public void EmployeeService_GetById_Customer(){
        long id = 1l;

        Employee addingEmployee = new Employee();
        Tasks tasks = new Tasks();
        Country country = new Country();
        Designation designation = new Designation();

        country.setCountry("Nigeria");
        designation.setDesignation("Full-Time");


        tasks.setTasks("Save");
        tasks.setEmployee(addingEmployee);

        addingEmployee.setEmployeeName("Aisha");
        addingEmployee.setCountry(country);
        addingEmployee.setDepartment("HR");
        addingEmployee.setDesignation(designation);
        addingEmployee.setDateOfJoining("12-01-2009");
        addingEmployee.setDateOfBirth("12-06-1989");
        addingEmployee.setMaritalStatus("Single");
        addingEmployee.setDateOfMarriage("Not-Married");
        addingEmployee.setDepartment("HR");
        addingEmployee.setTasks(List.of(tasks));

        when(employeeRepository.findById(id)).thenReturn(Optional.ofNullable(addingEmployee));
        AppResponse<EmployeeResponseFromDb> response = employeeService.getById(id);
        Assertions.assertThat(response).isNotNull();

    }
}
