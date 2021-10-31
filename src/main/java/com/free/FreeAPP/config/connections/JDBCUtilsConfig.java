package com.free.FreeAPP.config.connections;

/**
 * @Date 2021/10/18   9:02
 */

import java.io.InputStream;
import java.util.Properties;

public class JDBCUtilsConfig {
    private String driverClass;
    private String url;
    private String username;
    private String password;

    public JDBCUtilsConfig() {
    }

    public String getDriverClass() {
        return this.driverClass;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void readConfig() throws Exception {
        InputStream in = JDBCUtilsConfig.class.getClassLoader().getResourceAsStream("database.properties");
        Properties pro = new Properties();
        pro.load(in);
        this.driverClass = pro.getProperty("driverClass");
        this.url = pro.getProperty("url");
        this.username = pro.getProperty("username");
        this.password = pro.getProperty("password");
    }
}