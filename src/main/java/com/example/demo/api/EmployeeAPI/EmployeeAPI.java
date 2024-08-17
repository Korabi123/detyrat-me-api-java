package com.example.demo.EmployeeAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EmployeeAPI.EmployeeModel.EmployeeStatus;
import com.example.demo.EmployeeAPI.EmployeeModel.EmployeeType;

@RestController
public class EmployeeAPI {
  private final List<EmployeeModel> employees = new ArrayList<EmployeeModel>();

  @GetMapping("/api/employees/get")
  public List<EmployeeModel> getEmployees(
    @RequestParam(value = "name", required = false) String name,
    @RequestParam(value = "email", required = false) String email,
    @RequestParam(value = "phone", required = false) String phone,
    @RequestParam(value = "satus", required = false) EmployeeStatus status,
    @RequestParam(value = "type", required = false) EmployeeType type
  ) {
    return employees.stream()
            .filter(employee -> (name == null || employee.getName().equals(name))
                    && (email == null || employee.getEmail().equals(email))
                    && (phone == null || employee.getPhone().equals(phone))
                    && (status == null || employee.getStatus().equals(status))
                    && (type == null || employee.getType().equals(type)))
            .collect(Collectors.toList());
  }

  @PostMapping("/api/employees/create")
  public List<EmployeeModel> createEmployee(
    @RequestBody List<EmployeeModel> newEmployees
  ) {
    for (EmployeeModel employeeModel : newEmployees) {
      this.employees.add(employeeModel);
    }
    return this.employees;
  }

  @PatchMapping("/api/employees/update")
  public List<EmployeeModel> updateEmployee(
    @RequestParam("employeeId") String employeeId,
    @RequestBody EmployeeModel updatedEmployee
  ) {
    employees.stream()
            .filter(employee -> employee.getId().equals(employeeId))
            .findFirst()
            .ifPresent(employee -> {
              employee.setName(updatedEmployee.getName());
              employee.setEmail(updatedEmployee.getEmail());
              employee.setPhone(updatedEmployee.getPhone());
              employee.setStatus(updatedEmployee.getStatus());
              employee.setType(updatedEmployee.getType());
            });
    return employees;
  }

  @DeleteMapping("/api/employees/delete")
  public List<EmployeeModel> deleteEmployee(
    @RequestParam("employeeId") String employeeId
  ) {
    employees.removeIf(employee -> employee.getId().equals(employeeId));
    return employees;
  }
}
