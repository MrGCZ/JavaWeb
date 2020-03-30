package com.gcz;

import javax.servlet.*;
import java.io.IOException;

public class HelloServlet implements Servlet {
    public HelloServlet() {
        System.out.println("1.Constructor");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("2.init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //service 方法是专门用来处理请求和响应的
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("3.service hello i am here!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("4.destroy");
    }
}
