package Ginika.Employee.GinikaEmployee.Controller;

import Ginika.Employee.GinikaEmployee.Dto.AppResponse;
import Ginika.Employee.GinikaEmployee.Dto.AuthenticationRequest;
import Ginika.Employee.GinikaEmployee.Dto.EmployeeDto;
import Ginika.Employee.GinikaEmployee.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public AppResponse<String> createCustomer(@RequestBody @Valid EmployeeDto employeeDto) {
        return authenticationService.create(employeeDto);
    }

    @PostMapping("/login")
    public AppResponse<String> login(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        return authenticationService.login(authenticationRequest);
    }
}
