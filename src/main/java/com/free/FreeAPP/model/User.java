package com.free.FreeAPP.model;

/**
 * @Date 2021/10/18   9:09
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(
        name = "t_user"
)
public class User implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private String name;
    private String intro;
    private String email;
    private String password;
    private String sex;
    private Integer status;
    private Timestamp create;
    private Timestamp update;

    public User() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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