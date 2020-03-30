package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.junit.Assert.*;


public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {

        Book book = new Book(null,"abc","James",new BigDecimal(12.3),100,2,null);
        bookDao.addBook(book);

    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        Book book = new Book(21,"abc","JamesBonds",new BigDecimal(12.3),100,2,null);
        bookDao.updateBook(book);
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
    }

    @Test
    public void queryForPageItems() {
        for (Book queryForPageItem : bookDao.queryForPageItemsByPrice(1,4,10,50)) {
            System.out.println(queryForPageItem);
        }
    }

}
