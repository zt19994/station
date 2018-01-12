package com.station.test;

import com.station.transaction.TransactionService;
import org.junit.Test;

public class TransactionTest {
    private TransactionService transactionService = new TransactionService();
    /**
    * 测试事务管理
    */
    @Test
    public void testTransaction(){
        System.out.println("testTransaction");
        try {
            transactionService.buyTicket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
