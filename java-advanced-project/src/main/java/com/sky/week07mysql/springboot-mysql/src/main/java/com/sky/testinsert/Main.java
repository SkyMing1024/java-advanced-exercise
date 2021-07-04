package com.sky.testinsert;

import com.sky.util.DBUtils;
import com.sky.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        final int num = 1000000;
        Connection connection = DBUtils.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO  t_order (user_id,sum_price,purchase_price,create_time,order_status) VALUES (?,?,?,?,?) ";
        PreparedStatement statement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            statement.setInt(1,1);
            statement.setString(2, String.valueOf(i));
            statement.setString(3, String.valueOf(i*0.9));
            statement.setString(4, DateUtil.getNowTime());
            statement.setString(5, String.valueOf(1));
            statement.addBatch();
            if (i%1000==0){
                System.out.println(i+" : "+i/1000);
                statement.executeBatch();
                statement.clearParameters();
                connection.commit();
            }
        }
        DBUtils.close();
        long diff =  (System.currentTimeMillis() - start)/1000;
        System.out.println(num+"条插入完毕，用时：" + diff + "s");
    }
}
