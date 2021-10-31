package com.free.FreeAPP.controller;

import com.free.FreeAPP.model.User;
import com.free.FreeAPP.service.impl.ServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Date 2021/10/19   10:58
 */
@Path("/comment")
public class commentsResource {
    @POST
    @Path("/review")
    @Produces(MediaType.TEXT_PLAIN)
    public String Review(@CookieParam("JSESSIONID") String id, @FormParam("password") String password, @FormParam("infoId") Integer infoId, @FormParam("secretsId") Integer secretsId, @FormParam("commentsValue") String commentsValue, @FormParam("create") Timestamp create) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        if (user.getId() != null) {
            boolean Service = service.ReviewService(user.getId(), commentsValue, infoId, secretsId, create);
            return Service ? "发表评论成功" : "发表评论失败";
        } else {
            return "密码错误";
        }
    }

    @GET
    @Path("/element")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<HashMap<String, String>> Element(@QueryParam("infoId") Integer infoId) {
        ServiceImpl service = new ServiceImpl();
        return service.CommentsValueListService(infoId);
    }

    @POST
    @Path("/confidentialMessage")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<HashMap<String, String>> ConfidentialMessage(@FormParam("secretsId") Integer secretsId) {
        ServiceImpl service = new ServiceImpl();
        return service.CommentSecretListService(secretsId);
    }
}