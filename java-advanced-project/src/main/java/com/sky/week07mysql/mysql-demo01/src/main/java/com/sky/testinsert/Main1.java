package com.sky.testinsert;

import com.sky.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main1 {

    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtils.getConnection();
        // 关闭事物自动提交
        connection.setAutoCommit(false);
        PreparedStatement pst = connection.prepareStatement("select count(*) num from t_user");
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("num"));
        }
        // 提交事物
        connection.commit();
        DBUtils.close();

    }
}
