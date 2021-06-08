package com.sky.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.List;

public class DBUtil {
    private static final String driver="com.mysql.cj.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3306/ry";
    private static final String name="root";
    private static final String pwd="root";
    private static Connection connection = null;
    private static PreparedStatement stmt;
    private static ResultSet rs;
    private static HikariDataSource dataSource = null;

    static {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, name, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // 构造Connection对象
    public static Connection getConnection()  {
        return connection;
    }

    public static void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("资源释放发生异常");
        }
    }

    public static HikariDataSource getDataSource(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(name);
        config.setPassword(pwd);

        dataSource = new HikariDataSource(config);
        return dataSource;
    }

}
