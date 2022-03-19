package com.alexchurs.rest.controller;

import com.alexchurs.rest.entity.Employee;
import com.alexchurs.rest.exceptions.EmployeeNotFoundException;
import com.alexchurs.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppRestController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Получение всех сотрудников
     */
    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmloyees = employeeService.getAllEmployees();

        return allEmloyees;
    }

    /**
     * Получение одного работника
     * @PathVariable используется для получения значения переменной из адреса запроса
     */
    @GetMapping("/employees/{id}")
    public Employee showEmployee(@PathVariable int id) {
            Employee employee = employeeService.getEmployee(id);

            if (employee == null) {
                throw new EmployeeNotFoundException("There is no employee with ID = " + id + " in Database");
            }

        return employee;
    }

    /**
     * Добавление нового сотрудника
     * @RequestBody связывает тело HTTP метода с параметром метода контроллера
     * @param employee - сотрудник
     * @return возращаем добавленного сотрудника с id
     */
    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {

        employeeService.saveEmployee(employee);
        return employee;
    }

    /**
     * Изменение существующего работника
     * @param employee - сотрудник
     * @return измененный сотрудник
     */
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {

        employeeService.saveEmployee(employee);
        return employee;
    }

    /**
     * Удаление сотрудника
     * @param id - id сотрудника
     * @return текст, информирующий о том, что сотрудник был удален
     */
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {

        Employee employee = employeeService.getEmployee(id);

        if (employee == null) {
            throw new EmployeeNotFoundException("There is no employee with ID = " + id + " in Database");
        }

        employeeService.deleteEmployee(id);
        return "Employee with ID = " + id + " was deleted";
    }

}
