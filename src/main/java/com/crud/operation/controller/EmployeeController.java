package com.crud.operation.controller;

import com.crud.operation.entity.Employee;
import com.crud.operation.jpa.EmployeeRepository;
import com.crud.operation.model.EmployeeModel;
import com.crud.operation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/resource")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/get/empName/{empName}")
    public ResponseEntity<List<Employee>> getEmployee(@PathVariable("empName") String empName) {
        List<Employee> allEmp = employeeService.getEmployee(empName);
        return new ResponseEntity<>(allEmp, HttpStatus.OK);
    }

    @GetMapping("/get/allEmp")
    public ResponseEntity<List<Employee>> getEmployee() {
        List<Employee> allEmp = employeeService.getAlltEmployee();
        return new ResponseEntity<>(allEmp, HttpStatus.OK);
    }

    @GetMapping("/get/allEmp/page")
    public ResponseEntity<List<Employee>> getEmployeePagination(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                @RequestParam(defaultValue = "2") Integer pageSize,
                                                                @RequestParam(defaultValue = "empName") String sortBy) {

        List<Employee> allEmp = employeeService.getAlltEmployeePagination(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(allEmp, HttpStatus.OK);
    }

    @GetMapping("/get/status/{active}")
    public ResponseEntity<List<Employee>> getAllActiveEmployee(@PathVariable("active") Integer activeStatus) {
        List<Employee> allEmp = employeeService.getAllActiveEmp(activeStatus);
        return new ResponseEntity<>(allEmp, HttpStatus.OK);
    }

    @GetMapping("/get/empNames")
    public ResponseEntity<List<Employee>> getAllEmpWithNames(@RequestParam("empNames") String empNames) {
        List<Employee> allEmp = employeeService.getAllempname(empNames);
        return new ResponseEntity<>(allEmp, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Employee> cerateEmployee(@RequestBody EmployeeModel employee) {
        Employee emp = employeeService.cerateEmployee(employee);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeModel employee) {
        Employee emp = (Employee) employeeService.updateEmployee(employee);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("empId") Long empId) {
        Employee emp = employeeService.deleteEmployee(empId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
