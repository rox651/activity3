package com.actividad3.actividad3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actividad3.actividad3.models.EmployeeEntity;
import com.actividad3.actividad3.repositories.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Iterable<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeEntity getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }

    public EmployeeEntity updateEmployee(Long id, EmployeeEntity employee) {
        EmployeeEntity employeeToUpdate = getEmployeeById(id);
        if (employeeToUpdate != null) {
            employeeToUpdate.setFirstName(employee.getFirstName());
            employeeToUpdate.setLastName(employee.getLastName());
            employeeToUpdate.setEmail(employee.getEmail());
            return employeeRepository.save(employeeToUpdate);
        } else {
            return null;
        }
    }

    public EmployeeEntity deleteEmployee(Long id) {
        EmployeeEntity employee = getEmployeeById(id);
        employeeRepository.delete(employee);
        return employee;
    }
}