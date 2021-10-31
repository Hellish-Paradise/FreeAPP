package com.free.FreeAPP.controller;

import com.free.FreeAPP.model.Code;
import com.free.FreeAPP.model.Secrets;
import com.free.FreeAPP.model.User;
import com.free.FreeAPP.service.impl.ServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Date 2021/10/21   16:30
 */
@Path("/secrets")
public class secretsResource {
    @POST
    @Path("/sent")
    @Produces("text/plain")
    public String SendSecret(@CookieParam("JSESSIONID") String id, @FormParam("password") String password, @FormParam("Topic") String topic, @FormParam("Value") String value, @FormParam("create") Timestamp create) {
        ServiceImpl service = new ServiceImpl();
        User user = service.AuthService(id, password);
        if (user.getId() != null) {
            Code code = service.GainCodeService(user.getId());
            if (code.getIsDelete() != null && code.getIsDelete().equals("N")) {
                boolean secretsService = service.SecretsService(topic, code.getCodePrivatekey(), value, user.getId(), create);
                if (secretsService) {
                    return "发表机密信息成功";
                } else {
                    return "发表机密信息失败";
                }
            } else {
                return "您还没有生成密钥";
            }
        } else {
            return "密码错误";
        }
    }

    @POST
    @Path("/obtain")
    @Produces(MediaType.APPLICATION_JSON)
    public Serializable ObtainSecret(@FormParam("secretId") Integer secretId, @FormParam("signature") String signature) {
        ServiceImpl service = new ServiceImpl();
        Secrets secrets = service.TakeOutSecretsService(secretId);
        if (secrets.getIsDelete() != null && secrets.getIsDelete().equals("N")) {
            if (secrets.getUserId() != null) {
                Code code = service.GainCodeService(secrets.getUserId());
                if (code.getCodePublickey() != null) {
                    Secrets captureService = service.CaptureService(secretId, secrets.getSecretsTopic(), code.getCodePublickey(), signature);
                    if (captureService != null) {
                        return captureService;
                    } else {
                        return "签名验证不通过";
                    }
                } else {
                    return "无此机密信息";
                }
            } else {
                return "无此机密信息";
            }
        } else {
            return "无此机密信息";
        }
    }
}