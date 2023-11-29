package Ginika.Employee.GinikaEmployee.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "EmployeeName", nullable = false)
    private String employeeName;

    @Column(name = "Department", nullable = false)
    private String department;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Designation designation;

    @Column(name = "Date_Of_Joining", nullable = false)
    private  String dateOfJoining;

    @Column(name = "Date_Of_Birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "Marital_Status", nullable = false)
    private  String maritalStatus;

    @Column(name = "Date_Of_Marriage", nullable = false)
    private  String dateOfMarriage;

    @Column(name = "Username", nullable = false)
    private  String username;

    @Column(name = "Password", nullable = false)
    private  String password;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;


    @OneToOne(cascade = CascadeType.PERSIST)
    private Country country;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Tasks> tasks;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Role roles;
}
