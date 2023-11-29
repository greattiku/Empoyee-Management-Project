package Ginika.Employee.GinikaEmployee.Dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
public class EmployeeDto {
    @NotEmpty(message = "EmployeeName can't be blank")
    private String employeeName;


    @NotEmpty(message = "Department can't be blank")
    private String department;


    @NotEmpty(message = "Designation can't be blank")
    private String designation;


    @NotEmpty(message = "Date_Of_Joining can't be blank")
    private  String dateOfJoining;


    @NotEmpty(message = "Date_Of_Birth can't be blank")
    private String dateOfBirth;


    @NotEmpty(message = "Marital_Status can't be blank")
    private  String maritalStatus;

    @NotEmpty(message = "Date_Of_Marriage can't be blank. If Single or Divorced, enter Not-Married")
    private  String dateOfMarriage;

    @NotEmpty(message = "Username can't be blank")
    private  String username;

    @NotEmpty(message = "Password can't be blank")
    private  String password;

    @NotEmpty(message = "email cant be blank")
    private String email;

    @NotEmpty(message = "Country can't be blank")
    private  String country;

    @NotEmpty(message = "Tasks can't be blank")
    private List<String> tasks;

    @NotEmpty(message = "Role can't be blank")
    private String role;
}
