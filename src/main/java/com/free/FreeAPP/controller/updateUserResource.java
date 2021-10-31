package com.free.FreeAPP.controller;

/**
 * @Date 2021/10/18   9:17
 */

import com.free.FreeAPP.model.User;
import com.free.FreeAPP.service.impl.ServiceImpl;

import javax.ws.rs.*;
import java.sql.Timestamp;

@Path("/userUpdate")
public class updateUserResource {
    public updateUserResource() {
    }

    @POST
    @Path("/nameUpdate")
    @Produces({"text/plain"})
    public String updateName(@CookieParam("JSESSIONID") String id, @FormParam("password") String password, @FormParam("name") String name, @FormParam("update") Timestamp update) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        if (user.getId() != null) {
            boolean nameUpdateService = service.NameUpdateService(user.getId(), name, update);
            return nameUpdateService ? "更新用户名成功" : "更新用户名失败";
        } else {
            return "密码错误";
        }
    }

    @POST
    @Path("/introUpdate")
    @Produces({"text/plain"})
    public String updateIntro(@CookieParam("JSESSIONID") String id, @FormParam("password") String password, @FormParam("intro") String intro, @FormParam("update") Timestamp update) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        if (user.getId() != null) {
            boolean nameUpdateService = service.IntroUpdateService(user.getId(), intro, update);
            return nameUpdateService ? "更新个人签名成功" : "更新个人签名失败";
        } else {
            return "密码错误";
        }
    }

    @POST
    @Path("/emailUpdate")
    @Produces({"text/plain"})
    public String updateEmail(@CookieParam("JSESSIONID") String id, @FormParam("password") String password, @FormParam("email") String email, @FormParam("update") Timestamp update) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        if (user.getId() != null) {
            boolean nameUpdateService = service.EmailUpdateService(user.getId(), email, update);
            boolean logoutService = service.LogoutService(id);
            return nameUpdateService && logoutService ? "更新Email成功" : "更新Email失败";
        } else {
            return "密码错误";
        }
    }

    @POST
    @Path("/passwordUpdate")
    @Produces({"text/plain"})
    public String updatePassword(@CookieParam("JSESSIONID") String id, @FormParam("password") String password, @FormParam("newPassword") String newPassword, @FormParam("update") Timestamp update) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        if (user.getId() != null) {
            Integer passwordUpdateService = service.PasswordUpdateService(user.getId(), newPassword, update);
            if (passwordUpdateService == 11) {
                boolean logoutService = service.LogoutService(id);
                return logoutService ? "更新密码成功" : "解除登录失败";
            } else {
                return passwordUpdateService == 1 ? "新密码不可以与旧密码相同" : "密码更新失败";
            }
        } else {
            return "原始密码错误";
        }
    }

    @POST
    @Path("/sexUpdate")
    @Produces({"text/plain"})
    public String updateSex(@CookieParam("JSESSIONID") String id, @FormParam("password") String password, @FormParam("sex") String sex, @FormParam("update") Timestamp update) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        if (user.getId() != null) {
            boolean nameUpdateService = service.SexUpdateService(user.getId(), sex, update);
            return nameUpdateService ? "更新性别成功" : "更新性别失败";
        } else {
            return "密码错误";
        }
    }

    @POST
    @Path("/statusUpdate")
    @Produces({"text/plain"})
    public String updateStatus(@CookieParam("JSESSIONID") String id, @FormParam("password") String password, @FormParam("update") Timestamp update) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        if (user.getId() != null) {
            boolean Service = service.DeregisterService(user.getId(), update);
            boolean logoutService = service.LogoutService(id);
            return Service && logoutService ? "注销成功(我们会继续保存您的账户请牢记您现在的密码与账户,感谢您使用我们的服务,期待您的回归)" : "注销失败";
        } else {
            return "密码错误";
        }
    }
}