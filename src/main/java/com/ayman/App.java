package com.ayman;
import java.sql.Date;

import com.ayman.dao.DBConnectivity;

import java.sql.*;

import com.ayman.dao.*;

public class App 
{
    public static void main( String[] args ) throws SQLException {
        Employee employee = new Employee(1,"Ahmed",true,new Date(System.currentTimeMillis()), 1000000);
        Employeedao dao = new EmpDaoImp();
        System.out.println(dao.findById(3));

    }
}
