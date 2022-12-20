package com.crud.operation.jpa;

import com.crud.operation.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePaginationAndSortingReporitory extends PagingAndSortingRepository<Employee,Long> {
}
