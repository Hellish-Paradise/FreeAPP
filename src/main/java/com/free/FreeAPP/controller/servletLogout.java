package com.free.FreeAPP.controller;

/**
 * @Date 2021/10/18   9:17
 */

import com.free.FreeAPP.model.Session;
import com.free.FreeAPP.service.impl.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "servletLogout",
        value = {"/servletLogout"}
)
public class servletLogout extends HttpServlet {
    public servletLogout() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; ++i) {
                if (cookies[i].getName().equals("JSESSIONID")) {
                    Session sessionLogout = new Session();
                    sessionLogout.setId(cookies[i].getValue());
                    ServiceImpl service = new ServiceImpl();
                    boolean logoutService = service.LogoutService(sessionLogout.getId());
                    if (logoutService) {
                        cookies[i].setMaxAge(0);
                        response.addCookie(cookies[i]);
                        out.println("登出成功");
                    } else {
                        out.println("登出失败");
                    }
                }
            }
        } else {
            out.println("您还没有登录");
        }

    }
}
