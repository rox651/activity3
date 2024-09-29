package com.actividad3.actividad3;

import com.actividad3.actividad3.models.EmployeeEntity;
import com.actividad3.actividad3.repositories.EmployeeRepository;
import com.actividad3.actividad3.services.EmployeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        EmployeeEntity employee1 = new EmployeeEntity();
        employee1.setFirstName("Homero");
        employee1.setLastName("Bracamonte");
        employee1.setEmail("homero@test.com");

        EmployeeEntity employee2 = new EmployeeEntity();
        employee2.setFirstName("Fabiana");
        employee2.setLastName("Rondón");
        employee2.setEmail("fabiana@test.com");

        employeeService.createEmployee(employee1);
        employeeService.createEmployee(employee2);

        when(employeeRepository.findAll()).thenReturn(List.of(employee1, employee2));

        Iterable<EmployeeEntity> employees = employeeService.getAllEmployees();
        List<EmployeeEntity> employeeList = (List<EmployeeEntity>) employees;

        assertNotNull(employeeList);
        assertEquals(2, employeeList.size());

        assertEquals("Homero", employeeList.get(0).getFirstName());
        assertEquals("Bracamonte", employeeList.get(0).getLastName());
        assertEquals("homero@test.com", employeeList.get(0).getEmail());

        assertEquals("Fabiana", employeeList.get(1).getFirstName());
        assertEquals("Rondón", employeeList.get(1).getLastName());
        assertEquals("fabiana@test.com", employeeList.get(1).getEmail());

    }

    @Test
    void testCreateEmployee() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setFirstName("Homero");
        employee.setLastName("Bracamonte");
        employee.setEmail("homero@test.com");

        when(employeeRepository.save(employee)).thenReturn(employee);

        EmployeeEntity employeeCreated = employeeService.createEmployee(employee);

        assertNotNull(employeeCreated);
        assertEquals("Homero", employeeCreated.getFirstName());
        assertEquals("Bracamonte", employeeCreated.getLastName());
        assertEquals("homero@test.com", employeeCreated.getEmail());
    }

    @Test
    void testEditEmployee() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(1L);
        employee.setFirstName("Homero");
        employee.setLastName("Bracamonte");
        employee.setEmail("homero@test.com");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);

        employee.setFirstName("Fabiana");
        employee.setLastName("Rondón");
        employee.setEmail("fabiana@test.com");

        EmployeeEntity employeeUpdated = employeeService.updateEmployee(employee.getId(), employee);

        assertNotNull(employeeUpdated);
        assertEquals("Fabiana", employeeUpdated.getFirstName());
        assertEquals("Rondón", employeeUpdated.getLastName());
        assertEquals("fabiana@test.com", employeeUpdated.getEmail());
    }

    @Test
    void testDeleteEmployee() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(1L);
        employee.setFirstName("Homero");
        employee.setLastName("Bracamonte");
        employee.setEmail("homero@test.com");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).delete(employee);

        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

    }
}
