package com.free.FreeAPP.controller;

/**
 * @Date 2021/10/18   9:04
 */

import com.free.FreeAPP.model.User;
import com.free.FreeAPP.service.impl.ServiceImpl;

import javax.ws.rs.*;
import java.io.Serializable;

@Path("/userAccess")
public class accessResource {
    public accessResource() {
    }

    @POST
    @Path("/userInfo")
    @Produces({"application/json"})
    public Serializable accessUser(@CookieParam("JSESSIONID") String id, @FormParam("password") String password) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        return (Serializable) (user.getId() != null ? service.UserAccessService(user.getId()) : "密码错误");
    }

    @GET
    @Path("/userAuth")
    @Produces({"text/plain"})
    public boolean loginAuth(@CookieParam("JSESSIONID") String id) {
        ServiceImpl service = new ServiceImpl();
        User user = service.LoginAuthService(id);
        return user.getId() != null;
    }
}
