package com.sky.util;


import java.sql.*;

public class DBUtils {
    private static final String driver="com.mysql.cj.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3306/test_sql";
    private static final String name="root";
    private static final String pwd="root";
    private static Connection connection = null;
    private static PreparedStatement stmt;
    private static ResultSet rs;

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

}
