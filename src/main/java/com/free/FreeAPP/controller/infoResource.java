package com.free.FreeAPP.controller;

import com.free.FreeAPP.model.User;
import com.free.FreeAPP.service.impl.ServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Date 2021/10/18   12:14
 */
@Path("/info")
public class infoResource {
    @POST
    @Path("/publish")
    @Produces(MediaType.TEXT_PLAIN)
    public String Publish(@CookieParam("JSESSIONID") String id, @FormParam("password") String password, @FormParam("infoTopic") String infoTopic, @FormParam("infoValue") String infoValue, @FormParam("create") Timestamp create) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        if (user.getId() != null) {
            boolean Service = service.PublishedInfoService(infoTopic, infoValue, user.getId(), create, create);
            return Service ? "发表信息成功" : "发表信息失败";
        } else {
            return "密码错误";
        }
    }

    @GET
    @Path("/access")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<HashMap<String, String>> Access() {
        ServiceImpl service = new ServiceImpl();
        return service.TopicListService();
    }

    @GET
    @Path("/particulars")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, String> Particulars(@QueryParam("infoId") Integer infoId) {
        ServiceImpl service = new ServiceImpl();
        return service.ValueListService(infoId);
    }
}