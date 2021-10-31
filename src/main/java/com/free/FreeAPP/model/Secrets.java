package com.free.FreeAPP.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Date 2021/10/21   15:53
 */
@Entity(
        name = "t_secrets"
)
public class Secrets implements Serializable {
    /**
     * 机密信息ID
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer secretsId;
    /**
     * 机密信息主题
     */
    private String secretsTopic;
    /**
     * 机密信息签名;对机密信息主题进行私钥加密
     */
    private String secretsSignature;
    /**
     * 机密信息内容;大文本机密信息内容
     */
    private String secretsValue;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 机密信息判断删除;是否进行逻辑删除
     */
    private String isDelete;
    /**
     * 创建时间
     */
    private Timestamp createdTime;
    /**
     * 更新时间
     */
    private Timestamp updatedTime;

    /**
     * 机密信息ID
     */
    public Integer getSecretsId() {
        return this.secretsId;
    }

    /**
     * 机密信息ID
     */
    public void setSecretsId(Integer secretsId) {
        this.secretsId = secretsId;
    }

    /**
     * 机密信息主题
     */
    public String getSecretsTopic() {
        return this.secretsTopic;
    }

    /**
     * 机密信息主题
     */
    public void setSecretsTopic(String secretsTopic) {
        this.secretsTopic = secretsTopic;
    }

    /**
     * 机密信息签名;对机密信息主题进行私钥加密
     */
    public String getSecretsSignature() {
        return this.secretsSignature;
    }

    /**
     * 机密信息签名;对机密信息主题进行私钥加密
     */
    public void setSecretsSignature(String secretsSignature) {
        this.secretsSignature = secretsSignature;
    }

    /**
     * 机密信息内容;大文本机密信息内容
     */
    public String getSecretsValue() {
        return this.secretsValue;
    }

    /**
     * 机密信息内容;大文本机密信息内容
     */
    public void setSecretsValue(String secretsValue) {
        this.secretsValue = secretsValue;
    }

    /**
     * 用户ID
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 机密信息判断删除;是否进行逻辑删除
     */
    public String getIsDelete() {
        return this.isDelete;
    }

    /**
     * 机密信息判断删除;是否进行逻辑删除
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 创建时间
     */
    public Timestamp getCreatedTime() {
        return this.createdTime;
    }

    /**
     * 创建时间
     */
    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 更新时间
     */
    public Timestamp getUpdatedTime() {
        return this.updatedTime;
    }

    /**
     * 更新时间
     */
    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }
}