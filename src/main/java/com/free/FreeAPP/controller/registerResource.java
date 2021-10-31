package com.free.FreeAPP.controller;

/**
 * @Date 2021/10/18   9:16
 */

import com.free.FreeAPP.model.User;
import com.free.FreeAPP.service.impl.ServiceImpl;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.Timestamp;

@Path("/register")
public class registerResource {
    public registerResource() {
    }

    @POST
    @Produces({"text/plain"})
    public String Register(@FormParam("name") String name, @FormParam("email") String email, @FormParam("password") String password, @FormParam("sex") String sex, @FormParam("create") Timestamp create, @FormParam("update") Timestamp update) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setSex(sex);
        user.setCreate(create);
        user.setUpdate(update);
        ServiceImpl service = new ServiceImpl();
        boolean registerService = service.RegisterService(user.getName(), user.getEmail(), user.getPassword(), user.getSex(), user.getCreate(), user.getUpdate());
        return registerService ? "注册成功" : "该Email已经注册或者注册内容有误导致失败";
    }
}
