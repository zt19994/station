package com.station.test;

import com.station.transaction.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-aop.xml")
public class TransactionTest2 {
    @Autowired
    private TransactionService transactionService;

    /**
    * 测试事务管理
    */
    @Test
    public void testTransactionTest2(){
        try {
            transactionService.buyTicket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
