package com.gcz.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("123");
        String downloadFileName="idea.txt";

        ServletContext servletContext = getServletContext();

        String mimeType = servletContext.getMimeType("/uploadfile/" + downloadFileName);
        System.out.println(mimeType);
        resp.setContentType(mimeType);
        resp.setHeader("Content-Disposition","attachment; filename=" + downloadFileName);

        InputStream resourceAsStream = servletContext.getResourceAsStream("/uploadfile/" + downloadFileName);
        ServletOutputStream outputStream = resp.getOutputStream();

        IOUtils.copy(resourceAsStream,outputStream);


    }
}
