package com.free.FreeAPP.controller;

import com.free.FreeAPP.model.User;
import com.free.FreeAPP.service.impl.ServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

/**
 * @Date 2021/10/23   20:17
 */
@Path("/management")
public class informationManagementResource {
    @POST
    @Path("/secret")
    @Produces(MediaType.APPLICATION_JSON)
    public Serializable Secret(@CookieParam("JSESSIONID") String id, @FormParam("password") String password) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        if (user.getId() != null) {
            return service.ManagementSecretService(user.getId());
        } else {
            return "密码错误";
        }
    }

    @POST
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public Serializable Info(@CookieParam("JSESSIONID") String id, @FormParam("password") String password) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        if (user.getId() != null) {
            return service.ManagementInfoService(user.getId());
        } else {
            return "密码错误";
        }
    }

    @POST
    @Path("/secretDelete")
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteSecret(@CookieParam("JSESSIONID") String id, @FormParam("secretId") Integer secretId) {
        ServiceImpl service = new ServiceImpl();
        User user = service.LoginAuthService(id);
        if (user.getId() != null) {
            boolean deleteSecretService = service.ManagementDeleteSecretService(user.getId(), secretId);
            if (deleteSecretService) {
                return "删除成功";
            } else {
                return "删除失败";
            }
        } else {
            return "您还没有登录";
        }
    }

    @POST
    @Path("/infoDelete")
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteInfo(@CookieParam("JSESSIONID") String id, @FormParam("infoId") Integer infoId) {
        ServiceImpl service = new ServiceImpl();
        User user = service.LoginAuthService(id);
        if (user.getId() != null) {
            boolean deleteInfoService = service.ManagementDeleteInfoService(user.getId(), infoId);
            if (deleteInfoService) {
                return "删除成功";
            } else {
                return "删除失败";
            }
        } else {
            return "您还没有登录";
        }
    }
}