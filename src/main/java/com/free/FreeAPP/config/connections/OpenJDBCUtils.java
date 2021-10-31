package com.free.FreeAPP.config.connections;

/**
 * @Date 2021/10/18   9:03
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class OpenJDBCUtils {
    private Connection con;

    public OpenJDBCUtils() {
    }

    public Connection getConection() {
        try {
            JDBCUtilsConfig config = new JDBCUtilsConfig();
            config.readConfig();
            Class.forName(config.getDriverClass());
            this.con = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return this.con;
    }
}