package Ginika.Employee.GinikaEmployee.Controller;

import Ginika.Employee.GinikaEmployee.Dto.AppResponse;
import Ginika.Employee.GinikaEmployee.Dto.EmployeeDto;
import Ginika.Employee.GinikaEmployee.Dto.EmployeeResponseFromDb;
import Ginika.Employee.GinikaEmployee.Entity.Employee;
import Ginika.Employee.GinikaEmployee.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PreAuthorize("hasRole('admin')")

    @GetMapping("/getEmployee")
    public AppResponse<Map<String, Object>> getEmployee(
            @RequestParam(value = "Page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "page", required = false, defaultValue = "3") int size
    ) {
        return employeeService.getAll(PageRequest.of(page, size));

    }


    @GetMapping("/{id}")
    public AppResponse<EmployeeResponseFromDb> getEmployeeById(@PathVariable long id) {
        return employeeService.getById(id);
    }

    @PatchMapping("/{id}")
    public AppResponse<String> update(@PathVariable long id, @Valid EmployeeDto employeedto) {
        return employeeService.update(employeedto);
    }

    @DeleteMapping("/{id}")
    public AppResponse<String> delete(@PathVariable long id, @Valid EmployeeDto employeedto) {
        return employeeService.delete(employeedto);
    }

}


        //select * from employee

//        @GetMapping("/getDepartment")
//        public AppResponse<List<Employee>> getByDepartment(String department) {
//            return employeeService.getByDepartment(department);
//        }


//        @GetMapping("/getEmployee")
//        public AppResponse<List<Employee>> getEmployee() {
//            return employeeService.getAll();
//        }