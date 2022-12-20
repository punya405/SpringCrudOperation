package com.crud.operation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "EMP_NAME")
    private String empName;

    @Column(name = "EMP_AGE")
    private Integer age;

    @Column(name = "EMP_ADD")
    private String address;

    @Column(name = "EMP_STATUS")
    private Integer empStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    private Address addressType;

}
