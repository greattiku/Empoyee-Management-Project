package Ginika.Employee.GinikaEmployee.Dto;

import Ginika.Employee.GinikaEmployee.Entity.Tasks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasksResponseFromDb {

    private Long id;

    private String thing;

    public TasksResponseFromDb (Tasks employee){
        id = employee.getId();
        thing = employee.getTasks();
    }

}

