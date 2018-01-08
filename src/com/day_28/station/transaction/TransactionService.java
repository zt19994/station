package com.day_28.station.transaction;

import com.day_28.station.util.JDBCUtil;

import java.sql.Connection;
import java.sql.Statement;

public class TransactionService {

    public void buyTicket() throws Exception {

        //调用工具类中的getConnection,返回连接
        //1.更新车票
        Connection connection = JDBCUtil.getConnection();
        Statement statement = connection.createStatement();

        //connection.setAutoCommit(false);
        String sql = "UPDATE ticket SET ticket_num=24 WHERE id=1";
        int i = statement.executeUpdate(sql);


        //System.out.println(1 / 0);
        //2.生成订单
        String sql2 = "INSERT INTO  ticket_order (ticket_id, user_id, num,state, order_num, create_time) VALUES (1, 5, 1,1, '104', now())";
        statement.executeUpdate(sql2);
        //connection.commit();
    }

}
