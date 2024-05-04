package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findOne(Long id){
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        if (foundEmployee.isPresent())
            return foundEmployee.get();

        return null;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }
}
