package com.free.FreeAPP.controller;

import com.free.FreeAPP.model.Code;
import com.free.FreeAPP.model.User;
import com.free.FreeAPP.service.impl.ServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Date 2021/10/20   13:50
 */
@Path("/code")
public class codeResource {
    @POST
    @Path("/keyGeneration")
    @Produces("text/plain")
    public String KeyGeneration(@CookieParam("JSESSIONID") String id, @FormParam("password") String password, @FormParam("create") Timestamp create) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        if (user.getId() != null) {
            Integer flag;
            do {
                flag = service.KeyGenerationService(user.getId(), create);
            } while (flag == 1);
            if (flag == 11) {
                return "私钥与公钥生成成功";
            } else {
                return "私钥与公钥生成失败";
            }
        } else {
            return "密码错误";
        }
    }

    @POST
    @Path("/gainKey")
    @Produces(MediaType.APPLICATION_JSON)
    public Serializable GainKey(@CookieParam("JSESSIONID") String id, @FormParam("password") String password) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        if (user.getId() != null) {
            Code code = service.GainCodeService(user.getId());
            if (code.getIsDelete() != null) {
                if (code.getIsDelete().equals("N")) {
                    return service.GainCodeService(user.getId());
                } else {
                    return "Empty";
                }
            } else {
                return "Empty";
            }
        } else {
            return "密码错误";
        }
    }

    @POST
    @Path("/deleteKey")
    @Produces(MediaType.TEXT_PLAIN)
    public String DeleteKey(@CookieParam("JSESSIONID") String id, @FormParam("password") String password) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        if (user.getId() != null) {
            Code code = service.GainCodeService(user.getId());
            if (code.getIsDelete() != null) {
                if (code.getIsDelete().equals("N")) {
                    boolean deleteCodeService = service.DeleteCodeService(user.getId());
                    if (deleteCodeService) {
                        return "删除密钥成功";
                    } else {
                        return "删除密钥失败";
                    }
                } else {
                    return "密钥已删除";
                }
            } else {
                return "密钥为空";
            }
        } else {
            return "密码错误";
        }
    }
}