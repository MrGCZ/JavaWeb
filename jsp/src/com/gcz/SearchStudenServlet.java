package com.gcz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchStudenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Student> studentList=new ArrayList<Student>();

        //这里做个简化，模拟在数据库中查询数据后返回
        for (int i = 0; i < 10; i++) {
            int t = i + 1;

            studentList.add(new Student(t,"name"+t,18+t));

        }
        //保存学生结果到request域中
        request.setAttribute("stuList",studentList);
        //转发到jsp页面用于展示
        request.getRequestDispatcher("/a.jsp").forward(request,response);
    }
}
