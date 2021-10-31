package com.free.FreeAPP.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Date 2021/10/19   9:45
 */
@Entity(
        name = "t_comments"
)
public class Comments implements Serializable {
    /**
     * 评论ID
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer commentsId;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 评论内容;大文本评论内容
     */
    private String commentsValue;
    /**
     * 信息ID
     */
    private Integer infoId;
    /**
     * 机密信息ID
     */
    private Integer secretsId;
    /**
     * 评论判断删除;是否进行逻辑删除
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
     * 评论ID
     */
    public Integer getCommentsId() {
        return this.commentsId;
    }

    /**
     * 评论ID
     */
    public void setCommentsId(Integer commentsId) {
        this.commentsId = commentsId;
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
     * 评论内容;大文本评论内容
     */
    public String getCommentsValue() {
        return this.commentsValue;
    }

    /**
     * 评论内容;大文本评论内容
     */
    public void setCommentsValue(String commentsValue) {
        this.commentsValue = commentsValue;
    }

    /**
     * 信息ID
     */
    public Integer getInfoId() {
        return this.infoId;
    }

    /**
     * 信息ID
     */
    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

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
     * 评论判断删除;是否进行逻辑删除
     */
    public String getIsDelete() {
        return this.isDelete;
    }

    /**
     * 评论判断删除;是否进行逻辑删除
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