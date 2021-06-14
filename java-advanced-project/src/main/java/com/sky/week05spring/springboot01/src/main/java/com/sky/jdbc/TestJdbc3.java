package com.sky.jdbc;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 测试Hikari 连接池
 */
public class TestJdbc3 {
    public static void main(String[] args) {
        HikariDataSource dataSource = DBUtil.getDataSource();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from sys_user where user_id = ?");
            pst.setString(1,"2");
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("user_id") + ":" + rs.getString("user_name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (connection!=null){
                    connection.close();
                }
                if (dataSource!=null){
                    dataSource.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
