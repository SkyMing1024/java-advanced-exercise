package com.sky.testinsert;

import com.sky.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2 {
    public static void main(String[] args) {
        Connection connection = DBUtils.getConnection();
        try {
        connection.setAutoCommit(false);
        String sql = "INSERT INTO  t_order (user_id,sum_price,purchase_price,create_time,order_status)\n" +
                "VALUE (?,?,?,?,?) ";
        PreparedStatement statement = connection.prepareStatement(sql);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String datetime = format.format(date);
        statement.setInt(1, 1);
        statement.setString(2, String.valueOf(100));
        statement.setString(3, String.valueOf(100 * 0.9));
        statement.setString(4, datetime);
        statement.setString(5, String.valueOf(1));


            boolean execute = statement.execute(sql);
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtils.close();

    }
}
