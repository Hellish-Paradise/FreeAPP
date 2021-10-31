package com.free.FreeAPP.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Date 2021/10/20   16:10
 */
@Entity(
        name = "t_code"
)
public class Code implements Serializable {
    /**
     * 加密ID
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer codeId;
    /**
     * 信息加密;信息加密密码
     */
    private String codeMessages;
    /**
     * 私钥加密;个人加密私钥
     */
    private String codePrivatekey;
    /**
     * 公钥解密;全员解密公钥
     */
    private String codePublickey;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 密码加密判断删除;是否进行逻辑删除
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
     * 加密ID
     */
    public Integer getCodeId() {
        return this.codeId;
    }

    /**
     * 加密ID
     */
    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    /**
     * 信息加密;信息加密密码
     */
    public String getCodeMessages() {
        return this.codeMessages;
    }

    /**
     * 信息加密;信息加密密码
     */
    public void setCodeMessages(String codeMessages) {
        this.codeMessages = codeMessages;
    }

    /**
     * 私钥加密;个人加密私钥
     */
    public String getCodePrivatekey() {
        return this.codePrivatekey;
    }

    /**
     * 私钥加密;个人加密私钥
     */
    public void setCodePrivatekey(String codePrivatekey) {
        this.codePrivatekey = codePrivatekey;
    }

    /**
     * 公钥解密;全员解密公钥
     */
    public String getCodePublickey() {
        return this.codePublickey;
    }

    /**
     * 公钥解密;全员解密公钥
     */
    public void setCodePublickey(String codePublickey) {
        this.codePublickey = codePublickey;
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
     * 密码加密判断删除;是否进行逻辑删除
     */
    public String getIsDelete() {
        return this.isDelete;
    }

    /**
     * 密码加密判断删除;是否进行逻辑删除
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