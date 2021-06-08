package com.sky.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 测试 PrepareStatement 事物
 */
public class TestJdbc2 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        // 关闭事物自动提交
        connection.setAutoCommit(false);
        PreparedStatement pst = connection.prepareStatement("select * from sys_user where user_id = ?");
        pst.setString(1,"2");
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("user_id") + ":" + rs.getString("user_name"));
        }
        pst = connection.prepareStatement("update sys_user set dept_id = ? where user_id = ?");
        pst.clearParameters();
        pst.setString(1,"105");
        pst.setString(2,"2");
        int i = pst.executeUpdate();
        // 提交事物
        connection.commit();
        DBUtil.close();

    }
}
