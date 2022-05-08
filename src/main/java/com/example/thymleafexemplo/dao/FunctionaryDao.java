package com.example.thymleafexemplo.dao;

import com.example.thymleafexemplo.database.DB;
import com.example.thymleafexemplo.model.Functionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FunctionaryDao  implements IFunctionaryDao{

    private Connection conn;

    @Autowired
    public FunctionaryDao(){
        conn = DB.getConnection();
    }

    @Override
    public List<Functionary> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM functionary;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Functionary> functionaries = new ArrayList<>();
            while(rs.next()){
                functionaries.add(Functionary.fromResultSet(rs));
            }
            return functionaries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    @Override
    public Functionary findById(long id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM functionary WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                return Functionary.fromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public boolean update(long id, Functionary functionary) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE functionary SET name = ?, salary = ?, numDep = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, functionary.getName());
            ps.setDouble(2, functionary.getSalary());
            ps.setInt(3, functionary.getNumDep());
            ps.setLong(4, id);

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected > 0){
                System.out.println(rowsAffected + " linhas afetadas");
            }

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public boolean deleteById(long id) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE functionary WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public Functionary insert(Functionary functionary) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO functionary(name, salary, numDep) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql + "", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, functionary.getName());
            ps.setDouble(2, functionary.getSalary());
            ps.setInt(3, functionary.getNumDep());
            int affectedRows = ps.executeUpdate();
            if(affectedRows > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    functionary.setId(rs.getLong(1));
                    return functionary;
                }
                DB.closeResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(ps);
        }

        return null;
    }
}
