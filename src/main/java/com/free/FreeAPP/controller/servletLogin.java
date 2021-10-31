package com.free.FreeAPP.controller;

/**
 * @Date 2021/10/18   9:16
 */

import com.free.FreeAPP.model.User;
import com.free.FreeAPP.service.impl.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(
        name = "servletLogin",
        value = {"/servletLogin"}
)
public class servletLogin extends HttpServlet {
    public servletLogin() {
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userAgent = request.getHeader("user-agent");
        PrintWriter out = response.getWriter();
        ServiceImpl service = new ServiceImpl();
        User user = service.LoginService(email, password);
        if (user.getStatus() != null && user.getStatus() == 1) {
            HttpSession session = request.getSession(true);
            Date timeFirst = new Date(session.getCreationTime());
            Date timeLast = new Date(session.getLastAccessedTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formatFirst = dateFormat.format(timeFirst);
            String formatLast = dateFormat.format(timeLast);
            boolean sessionService = service.SessionService(session.getId(), user.getId(), userAgent, Timestamp.valueOf(formatFirst), Timestamp.valueOf(formatLast));
            if (sessionService) {
                session.invalidate();
                out.println("登录成功");
            } else {
                session.invalidate();
                out.println("登录失败");
            }
        } else if (user.getStatus() != null && user.getStatus() == 3) {
            out.println("此账号已经封禁,请联系管理员解封");
        } else if (user.getStatus() != null && user.getStatus() == 0) {
            out.println("您好欢迎回归请联系管理员恢复账号或者重新注册一个账号");
        } else {
            out.println("用户不存在或者密码错误");
        }

    }
}