package com.sky.jdbc;

import java.sql.*;

/**
 * 测试JDBC
 */
public class TestJdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/ry";
        String name = "root";
        String pwd = "root";
        
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, name, pwd);
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from sys_user ");
        while (set.next()) {
            System.out.println(set.getString("user_id") + ":" + set.getString("user_name"));
        }
        DBUtil.close();
    }

}
