package com.sky.testinsert;

import com.sky.util.DBUtils;
import com.sky.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertByThead {

    public static void main(String[] args) {
        System.out.println(10001/100);
        System.out.println(2%100);
    }

    public void insert(int num,String threadName) throws SQLException {
        Connection connection = DBUtils.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO  t_order (user_id,sum_price,purchase_price,create_time,order_status,remark) " +
                " VALUES (?,?,?,?,?,?) ";
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < num; i++) {
            statement.setInt(1,1);
            statement.setString(2, String.valueOf(i));
            statement.setString(3, String.valueOf(i*0.9));
            statement.setString(4, DateUtil.getNowTime());
            statement.setString(5, String.valueOf(1));
            statement.setString(6, threadName);
            statement.addBatch();
        }

        int[] ints = statement.executeBatch();
        connection.commit();
//        DBUtils.close();
    }
}
