package com.free.FreeAPP.model;

/**
 * @Date 2021/10/18   9:11
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(
        name = "t_online"
)
public class Online implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private Integer number;
    private Integer userId;
    private String isDelete;
    private Timestamp create;
    private Timestamp update;

    public Online() {
    }

    public String getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreate() {
        return this.create;
    }

    public void setCreate(Timestamp create) {
        this.create = create;
    }

    public Timestamp getUpdate() {
        return this.update;
    }

    public void setUpdate(Timestamp update) {
        this.update = update;
    }
}