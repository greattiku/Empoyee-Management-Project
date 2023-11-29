package Ginika.Employee.GinikaEmployee.Service;

import Ginika.Employee.GinikaEmployee.Dto.AppResponse;
import Ginika.Employee.GinikaEmployee.Dto.EmployeeDto;
import Ginika.Employee.GinikaEmployee.Dto.EmployeeResponseFromDb;
import Ginika.Employee.GinikaEmployee.Entity.Country;
import Ginika.Employee.GinikaEmployee.Entity.Designation;
import Ginika.Employee.GinikaEmployee.Entity.Employee;
import Ginika.Employee.GinikaEmployee.Entity.Tasks;
import Ginika.Employee.GinikaEmployee.Exception.ApiException;
import Ginika.Employee.GinikaEmployee.Repository.DesignationRepository;
import Ginika.Employee.GinikaEmployee.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DesignationRepository designationRepository;
    public AppResponse<String> create(EmployeeDto employeedto)  {
        Employee addingemployee = new Employee();
        addingemployee.setEmployeeName(employeedto.getEmployeeName());
        addingemployee.setDepartment(employeedto.getDepartment());
        addingemployee.setDateOfBirth(employeedto.getDateOfBirth());
        addingemployee.setDateOfJoining(employeedto.getDateOfJoining());
        addingemployee.setMaritalStatus(employeedto.getMaritalStatus());
        addingemployee.setDateOfMarriage(employeedto.getDateOfMarriage());
        addingemployee.setUsername(employeedto.getUsername());
        addingemployee.setPassword(employeedto.getPassword());
        addingemployee.setEmail(employeedto.getEmail());

        addingemployee.setDesignation(returnDesignation(employeedto));
        addingemployee.setTasks(returnTasks(employeedto,addingemployee));

        Country country = new Country();
        country.setCountry(employeedto.getCountry());
        addingemployee.setCountry(country);

        employeeRepository.save(addingemployee);
        return new AppResponse<>(0, "Successful");
    }

    public AppResponse<Map<String, Object>> getAll(Pageable pageable) {
        Page<EmployeeResponseFromDb> employeeResponseFromDbPage = employeeRepository.findAll(pageable).map((c) -> new EmployeeResponseFromDb(c));

        Map<String, Object> page = Map.of(
                "page", employeeResponseFromDbPage.getNumber(),
                "totalPages", employeeResponseFromDbPage.getTotalPages(),
                "totalELments", employeeResponseFromDbPage.getTotalElements(),
                "size", employeeResponseFromDbPage.getSize(),
                "content", employeeResponseFromDbPage.getContent()
        );


        return new AppResponse<>("success", page);
    }

    public AppResponse<List<Employee>> getAll(){
        List<Employee> chuks = employeeRepository.findAll();
        return new AppResponse<>(0,"Successful", chuks);
    }
    public AppResponse<EmployeeResponseFromDb> getById(long  id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ApiException("id not found"));
        EmployeeResponseFromDb emp = new EmployeeResponseFromDb(employee);
        return new AppResponse<>(0, "successful", emp);

    }


    public Designation returnDesignation(EmployeeDto emp){
        Designation designationDB = designationRepository.findByDesignation(emp.getDesignation());

        if (designationDB == null){
            Designation designation = new Designation();//instance or object of a class
            designation.setDesignation(emp.getDesignation());//setting it from the new object created
            return designation;
        }
            return designationDB;
    }

    public List<Tasks> returnTasks(EmployeeDto employeedto, Employee employee){
        List<String> tks = employeedto.getTasks();
        List<Tasks> tasksList = new ArrayList<>();
        //e.g Bought bought1 = new Bought();
//        Bought bought2 = new Bought();
//        Bought bought3 = new Bought();
//        Bought bought4 = new Bought();


        for (String t : tks){// the for each method prints out everything that has been added into our arraylist
            Tasks tasks = new Tasks();//an instance of bought is set just in case the user wants to buy a new thing
           tasks.setTasks(t);//the new thing bought by the user is set here
            tasks.setEmployee(employee);

            tasksList.add(tasks);//here the new thing bought is added back into our array list as {bought1, bought 2, bought, bought4}etc.
        }

        return tasksList;//here we return list of things the user bought
    }
    public AppResponse<String> update(EmployeeDto employeedto){
        Employee updatingemployee = new Employee();
        updatingemployee.setEmployeeName(employeedto.getEmployeeName());
        updatingemployee.setDepartment(employeedto.getDepartment());
        updatingemployee.setDateOfBirth(employeedto.getDateOfBirth());
        updatingemployee.setDateOfJoining(employeedto.getDateOfJoining());
        updatingemployee.setMaritalStatus(employeedto.getMaritalStatus());
        updatingemployee.setDateOfMarriage(employeedto.getDateOfMarriage());
        updatingemployee.setUsername(employeedto.getUsername());
        updatingemployee.setPassword(employeedto.getPassword());
        updatingemployee.setEmail(employeedto.getEmail());

        updatingemployee.setDesignation(returnDesignation(employeedto));
        updatingemployee.setTasks(returnTasks(employeedto,updatingemployee));

        Country country = new Country();
        country.setCountry(employeedto.getCountry());
        updatingemployee.setCountry(country);

        employeeRepository.save(updatingemployee);
        return new AppResponse<>(0, "Successful");
    }

    public AppResponse<String> delete(EmployeeDto employeedto){
        Employee deleteemployee = new Employee();
        deleteemployee.setEmployeeName(employeedto.getEmployeeName());
        deleteemployee.setDepartment(employeedto.getDepartment());
        deleteemployee.setDateOfBirth(employeedto.getDateOfBirth());
        deleteemployee.setDateOfJoining(employeedto.getDateOfJoining());
        deleteemployee.setMaritalStatus(employeedto.getMaritalStatus());
        deleteemployee.setDateOfMarriage(employeedto.getDateOfMarriage());
        deleteemployee.setUsername(employeedto.getUsername());
        deleteemployee.setPassword(employeedto.getPassword());
        deleteemployee.setEmail(employeedto.getEmail());

        deleteemployee.setDesignation(returnDesignation(employeedto));
        deleteemployee.setTasks(returnTasks(employeedto,deleteemployee));

        Country country = new Country();
        country.setCountry(employeedto.getCountry());
        deleteemployee.setCountry(country);

        employeeRepository.save(deleteemployee);
        return new AppResponse<>(0, "Successful");
    }
}




//    public AppResponse<List<Employee>> getByDepartment(String department){
//        List<Employee> chuks =  employeeRepository.getByDepartment(department);
//        return new AppResponse<>(0,"Successful", chuks);
//    }