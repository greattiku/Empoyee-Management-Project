package Ginika.Employee.GinikaEmployee.Dto;

import Ginika.Employee.GinikaEmployee.Entity.Employee;
import Ginika.Employee.GinikaEmployee.Entity.Tasks;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeResponseFromDb {
    private Long id;

    private String employeeName;

    private String department;

    private String designation;

    private  String dateOfJoining;

    private String dateOfBirth;

    private  String maritalStatus;

    private  String dateOfMarriage;

    private  String country;

    private List<TasksResponseFromDb> tasks;


    public EmployeeResponseFromDb(Employee employee){
        id = employee.getId();
        employeeName = employee.getEmployeeName();
        department = employee.getDepartment();
        designation = employee.getDesignation().getDesignation();
        dateOfJoining = employee.getDateOfJoining();
        dateOfBirth = employee.getDateOfBirth();
        maritalStatus = employee.getMaritalStatus();
        dateOfMarriage = employee.getDateOfMarriage();
        country = employee.getCountry().getCountry();
        tasks = convertFrmDbToResponse(employee);
    }

    public List<TasksResponseFromDb> convertFrmDbToResponse(Employee employee){
        List<Tasks> tasks = employee.getTasks();
        List<TasksResponseFromDb> bt = new ArrayList<>();

        for (Tasks b: tasks) {
            TasksResponseFromDb tasksResponseFromDb = new TasksResponseFromDb(b);

            bt.add(tasksResponseFromDb);
        }

        return bt;
    }
}

