package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;


    //ThreadLocal:为当前的线程关联一个数据（set()方法），组成键值对的形式，键为线程名
    //这里的思想是：线程安全思想，在这个线程中的任何地方取出（get()方法）这个关联的数据都是同一个对象，可以把数据库连接和线程绑定在一起。
    //这样就可以通过线程去找到对应的数据库连接，然后可以操作数据库的提交、回滚、关闭等（前提：取消掉autoCommit），使得数据库的操作满足事务性原则。
    //通过ThreadLocal实现数据库的事务性！
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(inputStream);
            // 创建 数据库连接 池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null,说明获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection getConnection(){

        Connection conn = conns.get();

        if (conn == null){
            try {
                conn = dataSource.getConnection(); //从数据库连接池中获取连接
                conns.set(conn); //保存到ThreadLocal对象中，供后面的jdbc使用
                conn.setAutoCommit(false);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return conn;
    }

    //提交事务，并关闭释放连接
    public static void commitAndClose(){
        Connection connection = conns.get();
        if(connection != null){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }
        //一定要执行remove操作，否则会报错（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();

    }



    //提交事务，并关闭释放连接
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if(connection != null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }
        //一定要执行remove操作，否则会报错（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();

    }



//    /**
//     * 关闭连接，放回数据库连接池
//     * @param conn
//     */
//    public static void close(Connection conn){
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
