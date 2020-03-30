package com.gcz.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (ServletFileUpload.isMultipartContent(req)){

            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            servletFileUpload.setHeaderEncoding("UTF-8");

            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);

                for (FileItem fileItem : list) {

                    if (fileItem.isFormField()){
                        System.out.println(fileItem.getFieldName());
                        System.out.println(fileItem.getString("UTF-8"));

                    }else{
                        System.out.println(fileItem.getFieldName());
                        System.out.println(fileItem.getName());

                        fileItem.write(new File("D:\\workspace\\JavaWeb\\jsp\\web\\uploadfile\\"+fileItem.getName()));
                    }

                }


            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
