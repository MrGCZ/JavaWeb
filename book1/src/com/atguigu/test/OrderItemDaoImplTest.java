package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest {

    @Test
    public void saveOrderItem() {

//        OrderDao orderDao = new OrderDaoImpl();
//
//        orderDao.saveOrder(new Order("1231223123",new Date(),new BigDecimal(1),0,1));
//

        OrderItemDao orderItemDao = new OrderItemDaoImpl();

        orderItemDao.saveOrderItem(new OrderItem(null,"123",1,new BigDecimal(1),new BigDecimal(1),"1231223123"));


    }
}