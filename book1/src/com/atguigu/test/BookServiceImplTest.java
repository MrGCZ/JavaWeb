package com.atguigu.test;


import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class BookServiceImplTest {

    private BookServiceImpl bookserviceImpl = new BookServiceImpl();

    @Test
    public void page() {

        System.out.println(bookserviceImpl.pageByPrice(1, 4,10,50));


    }
}
