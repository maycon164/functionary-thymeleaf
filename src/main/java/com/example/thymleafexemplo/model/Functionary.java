package com.example.thymleafexemplo.model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Functionary {

    private long id;
    private String name;
    private double salary;
    private int numDep;

    public static Functionary fromResultSet(ResultSet rs){

        Functionary fd = new Functionary();
        try {
            fd.setId(rs.getLong("id"));
            fd.setName(rs.getString("name"));
            fd.setSalary(rs.getDouble("salary"));
            fd.setNumDep(rs.getInt("numDep"));
            return fd;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
