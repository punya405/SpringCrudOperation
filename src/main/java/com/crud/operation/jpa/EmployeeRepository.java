package com.crud.operation.jpa;

import com.crud.operation.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //select * from employee where emp_name = '';
    List<Employee> findByEmpName(String empName);

    //JPQL
   // @Query(value = "Select * from Employee where EMP_STATUS = 1", nativeQuery = true)
    @Query(value = "Select es from Employee es where es.empStatus =:empStatus")
    List<Employee> findByActiveStatusEmp( @Param("empStatus")  Integer empStatus);

    @Query(value = "Select es from Employee es where es.empName  IN :empNames")
    List<Employee> findByNames( @Param("empNames")  List<String> empNames);

}
