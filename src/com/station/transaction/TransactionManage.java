package com.station.transaction;

import com.station.util.JDBCUtil;

public class TransactionManage {

    public void beforeBuyTicket() {
        System.out.println("beforeBuyTicket");
        try {
            JDBCUtil.getConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afterBuyTicket() {
        System.out.println("afterBuyTicket");
        try {
            JDBCUtil.getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rollBack() {
        System.out.println("rollBack");
        try {
            JDBCUtil.getConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
