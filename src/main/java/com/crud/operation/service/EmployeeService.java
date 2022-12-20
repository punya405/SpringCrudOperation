package com.crud.operation.service;

import com.crud.operation.entity.Address;
import com.crud.operation.entity.Employee;
import com.crud.operation.exception.EmployeeNotFound;
import com.crud.operation.jpa.EmployeePaginationAndSortingReporitory;
import com.crud.operation.jpa.EmployeeRepository;
import com.crud.operation.model.EmployeeModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);



    @Autowired
    private EmployeeRepository employeeRepository;

    @PostConstruct
    public void init(){
        Address address = new Address();
        address.setAddName("Blore");
        address.setAddType(1);
        Employee employee = new Employee();
        employee.setEmpName("ram");
        employee.setAddress("blore");
        employee.setAge(28);
        employee.setEmpStatus(1);
        employee.setAddressType(address);
        Address address1 = new Address();
        address1.setAddName("Delhi");
        address1.setAddType(1);
        Employee employee2 = new Employee();
        employee2.setEmpName("sam");
        employee2.setAddress("pune");
        employee2.setAge(22);
        employee2.setEmpStatus(1);
        employee2.setAddressType(address1);
        employeeRepository.saveAll(Arrays.asList(employee,employee2));
    }

    @Autowired
    private EmployeePaginationAndSortingReporitory employeePaginationAndSortingReporitory;

    public List<Employee> getEmployee(String empName) {
        List<Employee> allEmp = employeeRepository.findByEmpName(empName);
        return allEmp;
    }

    public List<Employee> getAlltEmployee() {
        List<Employee> allEmp = employeeRepository.findAll();
        log.error(" error log - fetched employees details ", allEmp);
        log.warn("warn log - fetched employees details ", allEmp);
        log.info("info - fetched employees details ", allEmp);
        log.debug("debug -fetched employees details ", allEmp);
        log.trace("trace -fetched employees details ", allEmp);
        return allEmp;
    }

    public List<Employee> getAlltEmployeePagination(Integer pageNo, Integer pageSize, String sortBy) {
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> allEmp = employeePaginationAndSortingReporitory.findAll(pageable);
        log.debug("fetched paginated employees details ", allEmp);
        if (allEmp.hasContent()) {
            return allEmp.getContent();
        }
        return Collections.emptyList();
    }


    public Employee cerateEmployee(EmployeeModel employee) {
        Employee employee1 = new Employee();
        employee1.setEmpName(employee.getEmpName());
        employee1.setAddress(employee.getAddress());
        employee1.setAge(employee.getAge());
        employee1.setEmpStatus(employee.getEmpStatus());
        Employee emp = employeeRepository.save(employee1);
        return emp;
    }

    public Object updateEmployee(EmployeeModel employee) {

        String existingEmpName = employee.getEmpName();
        List<Employee> existingEmployeeDetails = employeeRepository.findByEmpName(existingEmpName);

        if (CollectionUtils.isNotEmpty(existingEmployeeDetails)) {
            Employee employee1 = existingEmployeeDetails.get(0);
            employee1.setAge(employee.getAge());
            employee1.setAddress(employee.getAddress());
            employee1.setEmpName(employee.getEmpName());
            Employee emp = employeeRepository.save(employee1);
            return emp;
        } else {
            throw new EmployeeNotFound("Employee not found");
        }
    }


    public Employee deleteEmployee(Long empId) {

        Optional<Employee> empOpt = employeeRepository.findById(empId);
        if (empOpt.isPresent()) {
            employeeRepository.delete(empOpt.get());
            return empOpt.get();
        } else {
            throw new EmployeeNotFound("Employee not found");
        }
    }

    public List<Employee> getAllActiveEmp(Integer activeStatus) {
        return employeeRepository.findByActiveStatusEmp(activeStatus);
    }

    public List<Employee> getAllempname(String empName) {

        String[] allEmp = empName.split("\\,");
        List<String> empList = Stream.of(allEmp).collect(Collectors.toList());
        return employeeRepository.findByNames(empList);
    }
}
