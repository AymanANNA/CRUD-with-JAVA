package com.ayman.dao;

import java.util.List;

public interface Employeedao {

    List<Employee> findAll();

    Employee findById(int id);

    void save(Employee employee);

    void deletebyId(int id);

}
