package com.actividad3.actividad3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actividad3.actividad3.models.EmployeeEntity;
import com.actividad3.actividad3.services.EmployeeService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String getAllEmployees(Model model) {
        Iterable<EmployeeEntity> employees = employeeService.getAllEmployees();

        model.addAttribute("employees", employees);

        return "list-employees";
    }

    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable Long id, Model model) {
        EmployeeEntity employee = employeeService.getEmployeeById(id);

        model.addAttribute("employee", employee);

        return "employee";
    }

    // @PostMapping("/create-employee")
    // public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody
    // EmployeeEntity employee) {
    // EmployeeEntity newEmployee = employeeService.createEmployee(employee);
    // return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    // }

    // // // Actualizar un producto
    // @PutMapping("/{id}")
    // public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable Long id,
    // @RequestBody EmployeeEntity employee) {
    // EmployeeEntity newEmployee = employeeService.getEmployeeById(id);
    // if (newEmployee != null) {
    // newEmployee.setFirstName(employee.getFirstName());
    // newEmployee.setLastName(employee.getLastName());
    // newEmployee.setEmail(employee.getEmail());
    // return new ResponseEntity<>(employeeService.updateEmployee(id, newEmployee),
    // HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }

    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
    // employeeService.deleteEmployee(id);
    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }

}
