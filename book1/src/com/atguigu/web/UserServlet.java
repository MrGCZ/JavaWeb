package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    //升级：思路：把多个的selvet程序统一在一个里面，实际开发一个模块只需要一个servlet。
    // 使用type=“hidden”的表单项，通过表单项name：value的值来判断是什么提交。
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

//        if("login".equals(action)){
//            login(req,resp);
//        }else if("regist".equals(action)){
//            regist(req,resp);
//        }

        //可以使用反射优化多个elsif
        //这里的需求都是调用action字符串的同名方法
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            System.out.println(method);
            method.invoke(this,req, resp);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 调用 userService.login()登录处理业务
        User loginUser = userService.login(new User(null, username, password, null));
        // 如果等于null,说明登录 失败!
        if (loginUser == null) {
            //登入失败，需要把回显的数据保存在request域中，再回传给页面
            req.setAttribute("msg","用户名密码错误！");
            req.setAttribute("username",username);


            //   跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 登录 成功
            //保存用户登入之后的信息到session域中
            req.getSession().setAttribute("user",loginUser);
            //跳到成功页面login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //升级：BeanUtils直接解决获取request参数，以及把request参数传入对象的问题！
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

//        2、检查 验证码是否正确  === 写死,要求验证码为:abcde
        if (token!=null && token.equalsIgnoreCase(code)) {
//        3、检查 用户名是否可用
            if (userService.existsUsername(username)) {
                System.out.println("用户名[" + username + "]已存在!");
//        跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //      可用
//                调用Sservice保存到数据库
                userService.registUser(user);
//
//        跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            System.out.println("验证码[" + code + "]错误");
            //把需要回显页面的信息放到request域中
            req.setAttribute("msg", "验证码为abcde");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);


        }

    }


    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();

        resp.sendRedirect(req.getContextPath());
    }


}
