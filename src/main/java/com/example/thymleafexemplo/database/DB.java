package com.example.thymleafexemplo.database;

import java.sql.*;

public class DB {
    private static Connection connection;

    public static Connection getConnection(){
        if(connection != null){
            return connection;
        }else {
            try {
                String hostName = "localhost";
                String dbName = "functionary";
                String user = "SA";
                String senha = "Maycon@123";
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                connection = DriverManager.getConnection(String.format("jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s;",
                        hostName, dbName, user, senha));

                return connection;

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static void closeConnection(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeStatement(Statement st){
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
