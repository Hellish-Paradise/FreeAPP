package com.free.FreeAPP.config.connections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Date 2021/10/18   8:59
 */
public class CloseJDBCUtils {
    public CloseJDBCUtils() {
    }

    public void closeAll(Connection conn, Statement st, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException var7) {
                var7.printStackTrace();
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException var6) {
                var6.printStackTrace();
            }
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException var5) {
                var5.printStackTrace();
            }
        }

    }
}