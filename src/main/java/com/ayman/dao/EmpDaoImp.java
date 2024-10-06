package com.ayman.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EmpDaoImp implements Employeedao {
    @Override
    public List<Employee> findAll() {
        Connection connection = DBConnectivity.getConnection();
        if(connection == null){
            return null;
        }
        List<Employee> employees= new LinkedList<Employee>();
        String query = "SELECT * from employee";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet result = statement.executeQuery();//for querying use execute query and for create or update use executeUpdate
            while(result.next() /*comme itérator*/){
                Employee emp = new Employee(result.getInt("id"),result.getString("name"),result.getBoolean("gender"),result.getDate("birth_date"),result.getDouble("salary"));
                employees.add(emp);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return employees;
    }

    @Override
    public Employee findById(int id) {
        Connection connection = DBConnectivity.getConnection();
        Employee emp = null;
        if(connection == null){
            return null;
        }
        String query = "SELECT * FROM employee WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                emp = new Employee(result.getInt("id"),result.getString("name"),result.getBoolean("gender"),result.getDate("birth_date"),result.getDouble("salary"));
            }
            else {
                // Gérer le cas où aucun employé n'a été trouvé
                System.out.println("Aucun employé trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return emp;
    }

    @Override
    public void save(Employee employee) {
        Connection connection = DBConnectivity.getConnection();
        if(connection == null){
            return;
        }
        if(employee.getId() > 0){//update
            String query = "UPDATE employee SET name=?, gender=?, birth_date=?, salary=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query); ){

                statement.setString(1, employee.getName());
                statement.setBoolean(2, employee.isGender());
                statement.setDate(3, employee.getBirthDate());
                statement.setDouble(4, employee.getSalary());
                statement.setInt(5, employee.getId());

                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        else{//create in the case that we dont know the id (null)
            String query = "INSERT INTO employee (name,gender,birth_date,salary) VALUES(?,?,?,?);";
            try (PreparedStatement statement = connection.prepareStatement(query); ){

                statement.setString(1, employee.getName());
                statement.setBoolean(2, employee.isGender());
                statement.setDate(3, employee.getBirthDate());
                statement.setDouble(4, employee.getSalary());

                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void deletebyId(int id) {
        Connection connection = DBConnectivity.getConnection();
        if(connection == null){
            return;
        }
        String query = "DELETE FROM employee WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


//whenever you have a table and you want to make its crud you need to create a package dao
//this package dao manage this table  by adding connectivity to DB and having the model of the table
//implementing the crud for the table