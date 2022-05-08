package com.example.thymleafexemplo.dao;

import com.example.thymleafexemplo.database.DB;
import com.example.thymleafexemplo.model.Functionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Functionary update(long id, Functionary functionary) {
        return null;
    }

    @Override
    public Functionary delete(long id) {
        return null;
    }

    @Override
    public Functionary save(Functionary functionary) {
        return null;
    }
}
