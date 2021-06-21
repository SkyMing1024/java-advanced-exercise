package com.sky.testinsert;

import com.sky.util.DBUtils;
import com.sky.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        final int num = 100;
        Connection connection = DBUtils.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO  t_order (user_id,sum_price,purchase_price,order_status)\n" +
                "VALUE(?,?,?,?) ";
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < num; i++) {
            statement.setInt(1,1);
            statement.setString(2, String.valueOf(i));
            statement.setString(3, String.valueOf(i*0.9));
            statement.setString(4, String.valueOf(1));
        }

        long start = System.currentTimeMillis();

        statement.executeBatch();
        connection.commit();
        DBUtils.close();

        long diff =  (System.currentTimeMillis() - start)/1000;
        System.out.println(num+"条插入完毕，用时：" + diff + "s");
    }
}
