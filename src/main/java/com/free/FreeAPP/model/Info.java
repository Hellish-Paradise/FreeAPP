package com.free.FreeAPP.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Date 2021/10/18   11:16
 */
@Entity(
        name = "t_info"
)
public class Info implements Serializable {
    /**
     * 信息ID
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer infoId;
    /**
     * 信息主题
     */
    private String infoTopic;
    /**
     * 信息内容;大文本信息内容
     */
    private String infoValue;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 信息内容判断删除;是否进行逻辑删除
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
     * 信息主题
     */
    public String getInfoTopic() {
        return this.infoTopic;
    }

    /**
     * 信息主题
     */
    public void setInfoTopic(String infoTopic) {
        this.infoTopic = infoTopic;
    }

    /**
     * 信息内容;大文本信息内容
     */
    public String getInfoValue() {
        return this.infoValue;
    }

    /**
     * 信息内容;大文本信息内容
     */
    public void setInfoValue(String infoValue) {
        this.infoValue = infoValue;
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
     * 信息内容判断删除;是否进行逻辑删除
     */
    public String getIsDelete() {
        return this.isDelete;
    }

    /**
     * 信息内容判断删除;是否进行逻辑删除
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