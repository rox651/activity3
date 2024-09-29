package com.actividad3.actividad3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actividad3.actividad3.models.EmployeeEntity;
import com.actividad3.actividad3.services.EmployeeService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String getAllEmployees(Model model) {
        Iterable<EmployeeEntity> employees = employeeService.getAllEmployees();

        model.addAttribute("employees", employees);

        return "list-employees";
    }

    @GetMapping("/employees/{id}")
    public String getEmployeeById(@PathVariable Long id, Model model) {
        EmployeeEntity employee = employeeService.getEmployeeById(id);

        model.addAttribute("employee", employee);

        return "employee";
    }

    @GetMapping("/employees/{id}/edit")
    public String editEmployee(@PathVariable Long id, Model model) {
        EmployeeEntity employee = employeeService.getEmployeeById(id);

        model.addAttribute("employee", employee);

        return "edit-employee";
    }

    @GetMapping("/employees/{id}/delete")
    public String removeEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }

    @GetMapping("/add-employee")
    public String addEmployee(Model model) {
        model.addAttribute("newEmployee", new EmployeeEntity(null, null, null));
        return "add-employee";
    }

    @PostMapping("/create-employee")
    public String createEmployee(@ModelAttribute EmployeeEntity newEmployee) {
        employeeService.createEmployee(newEmployee);
        return "redirect:/";
    }

    @PostMapping("/update-employee/{id}")
    public String updateEmployee(@PathVariable Long id,
            @ModelAttribute EmployeeEntity employee) {

        EmployeeEntity newEmployee = employeeService.getEmployeeById(id);
        newEmployee.setFirstName(employee.getFirstName());
        newEmployee.setLastName(employee.getLastName());
        newEmployee.setEmail(employee.getEmail());

        employeeService.updateEmployee(id, newEmployee);

        return "redirect:/";
    }

}
