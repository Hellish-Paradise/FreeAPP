package com.free.FreeAPP.model;

/**
 * @Date 2021/10/18   9:10
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(
        name = "t_session"
)
public class Session implements Serializable {
    @Id
    private String id;
    private Integer userId;
    private String terminal;
    private Timestamp create;
    private Timestamp update;

    public Session() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTerminal() {
        return this.terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
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