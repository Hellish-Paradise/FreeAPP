package com.free.FreeAPP.dao.impl;

/**
 * @Date 2021/10/18   9:06
 */

import com.free.FreeAPP.config.connections.CloseJDBCUtils;
import com.free.FreeAPP.config.connections.OpenJDBCUtils;
import com.free.FreeAPP.dao.IDao;
import com.free.FreeAPP.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DaoImpl implements IDao {
    public DaoImpl() {
    }

    public boolean RegisterDao(String name, String email, String password, String sex, Integer status, Timestamp create, Timestamp update) {
        boolean judgement = false;
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        CallableStatement statement = null;
        ResultSet resultSet = null;
        boolean var18 = false;

        CloseJDBCUtils closeJDBCUtils;
        label93:
        {
            try {
                var18 = true;
                String sql = "{CALL register(?,?,?,?,?,?,?)}";
                statement = conection.prepareCall(sql);
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, password);
                statement.setString(4, sex);
                statement.setInt(5, status);
                statement.setTimestamp(6, create);
                statement.setTimestamp(7, update);
                resultSet = statement.executeQuery();

                while (true) {
                    while (resultSet.next()) {
                        if (resultSet.getInt("outs") == 0) {
                            judgement = true;
                        } else if (resultSet.getInt("outs") == 1 || resultSet.getInt("outs") == 3) {
                            judgement = false;
                        }
                    }

                    var18 = false;
                    break label93;
                }
            } catch (Exception var19) {
                var19.printStackTrace();
                judgement = false;
                var18 = false;
            } finally {
                if (var18) {
                    CloseJDBCUtils var15 = new CloseJDBCUtils();
                    var15.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return judgement;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return judgement;
    }

    public User LoginDao(String email, String password) {
        User user = new User();
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean var13 = false;

        CloseJDBCUtils closeJDBCUtils;
        label67:
        {
            try {
                var13 = true;
                String sql = "SELECT user_id,user_status FROM t_user WHERE user_email = ? AND user_password = ?";
                statement = conection.prepareStatement(sql);
                statement.setString(1, email);
                statement.setString(2, password);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    user.setId(resultSet.getInt("user_id"));
                    user.setStatus(resultSet.getInt("user_status"));
                }

                var13 = false;
                break label67;
            } catch (Exception var14) {
                var14.printStackTrace();
                var13 = false;
            } finally {
                if (var13) {
                    CloseJDBCUtils var10 = new CloseJDBCUtils();
                    var10.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return user;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return user;
    }

    public boolean SessionDao(String id, Integer userId, String terminal, Timestamp create, Timestamp update) {
        boolean judgement = false;
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        CallableStatement statement = null;
        ResultSet resultSet = null;
        boolean var16 = false;

        CloseJDBCUtils closeJDBCUtils;
        label77:
        {
            try {
                var16 = true;
                String sql = "{CALL login(?,?,?,?,?)}";
                statement = conection.prepareCall(sql);
                statement.setString(1, id);
                statement.setInt(2, userId);
                statement.setString(3, terminal);
                statement.setTimestamp(4, create);
                statement.setTimestamp(5, update);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    if (resultSet.getInt("outs") == 1) {
                        judgement = true;
                    } else if (resultSet.getInt("outs") == 0) {
                        judgement = false;
                    }
                }

                var16 = false;
                break label77;
            } catch (Exception var17) {
                var17.printStackTrace();
                judgement = false;
                var16 = false;
            } finally {
                if (var16) {
                    CloseJDBCUtils var13 = new CloseJDBCUtils();
                    var13.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return judgement;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return judgement;
    }

    public boolean LogoutDao(String id) {
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        boolean var13 = false;

        CloseJDBCUtils closeJDBCUtils;
        boolean judgement;
        label66:
        {
            try {
                var13 = true;
                conection.setAutoCommit(false);
                String sql = "DELETE FROM t_session WHERE session_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setString(1, id);
                statement.executeUpdate();
                conection.commit();
                judgement = true;
                var13 = false;
                break label66;
            } catch (Exception var15) {
                try {
                    conection.rollback();
                } catch (SQLException var14) {
                    var14.printStackTrace();
                    judgement = false;
                }

                var15.printStackTrace();
                judgement = false;
                var13 = false;
            } finally {
                if (var13) {
                    CloseJDBCUtils var9 = new CloseJDBCUtils();
                    var9.closeAll(conection, statement, (ResultSet) null);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
            return judgement;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
        return judgement;
    }

    public User AuthDao(String id, String password) {
        User user = new User();
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean var13 = false;

        CloseJDBCUtils closeJDBCUtils;
        label67:
        {
            try {
                var13 = true;
                String sql = "SELECT u.user_id FROM t_session AS s INNER JOIN t_user AS u ON s.user_id=u.user_id WHERE s.session_id=? AND u.user_password=?";
                statement = conection.prepareStatement(sql);
                statement.setString(1, id);
                statement.setString(2, password);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    user.setId(resultSet.getInt("u.user_id"));
                }

                var13 = false;
                break label67;
            } catch (Exception var14) {
                var14.printStackTrace();
                var13 = false;
            } finally {
                if (var13) {
                    CloseJDBCUtils var10 = new CloseJDBCUtils();
                    var10.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return user;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return user;
    }

    public User UserAccessDao(Integer id) {
        User user = new User();
        String sex = null;
        String sexc = null;
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean var16 = false;

        CloseJDBCUtils closeJDBCUtils;
        label115:
        {
            try {
                var16 = true;
                String sql = "SELECT user_name,user_intro,user_email,user_sex,CREATED_TIME,UPDATED_TIME FROM t_user WHERE user_id =?";
                statement = conection.prepareStatement(sql);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    user.setName(resultSet.getString("user_name"));
                    user.setIntro(resultSet.getString("user_intro"));
                    user.setEmail(resultSet.getString("user_email"));
                    sexc = resultSet.getString("user_sex");
                    user.setCreate(resultSet.getTimestamp("CREATED_TIME"));
                    user.setUpdate(resultSet.getTimestamp("UPDATED_TIME"));
                }

                byte var11 = -1;
                switch (sexc.hashCode()) {
                    case 77:
                        if (sexc.equals("M")) {
                            var11 = 0;
                        }
                        break;
                    case 83:
                        if (sexc.equals("S")) {
                            var11 = 2;
                        }
                        break;
                    case 87:
                        if (sexc.equals("W")) {
                            var11 = 1;
                        }
                }

                switch (var11) {
                    case 0:
                        sex = "男";
                        break;
                    case 1:
                        sex = "女";
                        break;
                    case 2:
                        sex = "保密";
                }

                user.setSex(sex);
                var16 = false;
                break label115;
            } catch (Exception var17) {
                var17.printStackTrace();
                var16 = false;
            } finally {
                if (var16) {
                    CloseJDBCUtils var13 = new CloseJDBCUtils();
                    var13.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return user;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return user;
    }

    public boolean NameUpdateDao(Integer id, String name, Timestamp update) {
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        boolean var15 = false;

        boolean judgement;
        CloseJDBCUtils closeJDBCUtils;
        label66:
        {
            try {
                var15 = true;
                conection.setAutoCommit(false);
                String sql = "UPDATE t_user SET user_name = ?,UPDATED_TIME = ? WHERE user_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setString(1, name);
                statement.setTimestamp(2, update);
                statement.setInt(3, id);
                statement.executeUpdate();
                conection.commit();
                judgement = true;
                var15 = false;
                break label66;
            } catch (Exception var17) {
                try {
                    conection.rollback();
                } catch (SQLException var16) {
                    var16.printStackTrace();
                    judgement = false;
                }

                var17.printStackTrace();
                judgement = false;
                var15 = false;
            } finally {
                if (var15) {
                    CloseJDBCUtils var11 = new CloseJDBCUtils();
                    var11.closeAll(conection, statement, (ResultSet) null);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
            return judgement;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
        return judgement;
    }

    public boolean IntroUpdateDao(Integer id, String intro, Timestamp update) {
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        boolean var15 = false;

        boolean judgement;
        CloseJDBCUtils closeJDBCUtils;
        label66:
        {
            try {
                var15 = true;
                conection.setAutoCommit(false);
                String sql = "UPDATE t_user SET user_intro = ?,UPDATED_TIME = ? WHERE user_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setString(1, intro);
                statement.setTimestamp(2, update);
                statement.setInt(3, id);
                statement.executeUpdate();
                conection.commit();
                judgement = true;
                var15 = false;
                break label66;
            } catch (Exception var17) {
                try {
                    conection.rollback();
                } catch (SQLException var16) {
                    var16.printStackTrace();
                    judgement = false;
                }

                var17.printStackTrace();
                judgement = false;
                var15 = false;
            } finally {
                if (var15) {
                    CloseJDBCUtils var11 = new CloseJDBCUtils();
                    var11.closeAll(conection, statement, (ResultSet) null);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
            return judgement;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
        return judgement;
    }

    public boolean EmailUpdateDao(Integer id, String email, Timestamp update) {
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        boolean var15 = false;

        boolean judgement;
        CloseJDBCUtils closeJDBCUtils;
        label66:
        {
            try {
                var15 = true;
                conection.setAutoCommit(false);
                String sql = "UPDATE t_user SET user_email = ?,UPDATED_TIME = ? WHERE user_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setString(1, email);
                statement.setTimestamp(2, update);
                statement.setInt(3, id);
                statement.executeUpdate();
                conection.commit();
                judgement = true;
                var15 = false;
                break label66;
            } catch (Exception var17) {
                try {
                    conection.rollback();
                } catch (SQLException var16) {
                    var16.printStackTrace();
                    judgement = false;
                }

                var17.printStackTrace();
                judgement = false;
                var15 = false;
            } finally {
                if (var15) {
                    CloseJDBCUtils var11 = new CloseJDBCUtils();
                    var11.closeAll(conection, statement, (ResultSet) null);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
            return judgement;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
        return judgement;
    }

    public boolean SexUpdateDao(Integer id, String sex, Timestamp update) {
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        boolean var15 = false;

        boolean judgement;
        CloseJDBCUtils closeJDBCUtils;
        label66:
        {
            try {
                var15 = true;
                conection.setAutoCommit(false);
                String sql = "UPDATE t_user SET user_sex = ?,UPDATED_TIME = ? WHERE user_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setString(1, sex);
                statement.setTimestamp(2, update);
                statement.setInt(3, id);
                statement.executeUpdate();
                conection.commit();
                judgement = true;
                var15 = false;
                break label66;
            } catch (Exception var17) {
                try {
                    conection.rollback();
                } catch (SQLException var16) {
                    var16.printStackTrace();
                    judgement = false;
                }

                var17.printStackTrace();
                judgement = false;
                var15 = false;
            } finally {
                if (var15) {
                    CloseJDBCUtils var11 = new CloseJDBCUtils();
                    var11.closeAll(conection, statement, (ResultSet) null);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
            return judgement;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
        return judgement;
    }

    public Integer PasswordUpdateDao(Integer id, String password, Timestamp update) {
        int StatusNumber = 0;
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        CallableStatement statement = null;
        ResultSet resultSet = null;
        boolean var14 = false;

        CloseJDBCUtils closeJDBCUtils;
        label69:
        {
            try {
                var14 = true;
                String sql = "{CALL passwordUpdate(?,?,?)}";
                statement = conection.prepareCall(sql);
                statement.setInt(1, id);
                statement.setString(2, password);
                statement.setTimestamp(3, update);

                for (resultSet = statement.executeQuery(); resultSet.next(); StatusNumber = resultSet.getInt("outs")) {
                }

                var14 = false;
                break label69;
            } catch (Exception var15) {
                var15.printStackTrace();
                var14 = false;
            } finally {
                if (var14) {
                    CloseJDBCUtils var11 = new CloseJDBCUtils();
                    var11.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return StatusNumber;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return StatusNumber;
    }

    public User LoginAuthDao(String id) {
        User user = new User();
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean var12 = false;

        CloseJDBCUtils closeJDBCUtils;
        label67:
        {
            try {
                var12 = true;
                String sql = "SELECT u.user_id FROM t_session AS s INNER JOIN t_user AS u ON s.user_id=u.user_id WHERE s.session_id=?";
                statement = conection.prepareStatement(sql);
                statement.setString(1, id);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    user.setId(resultSet.getInt("u.user_id"));
                }

                var12 = false;
                break label67;
            } catch (Exception var13) {
                var13.printStackTrace();
                var12 = false;
            } finally {
                if (var12) {
                    CloseJDBCUtils var9 = new CloseJDBCUtils();
                    var9.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return user;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return user;
    }

    public boolean DeregisterDao(Integer id, Timestamp update) {
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        boolean var14 = false;

        CloseJDBCUtils closeJDBCUtils;
        boolean judgement;
        label66:
        {
            try {
                var14 = true;
                conection.setAutoCommit(false);
                String sql = "UPDATE t_user SET user_status = ?,UPDATED_TIME = ? WHERE user_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setInt(1, 0);
                statement.setTimestamp(2, update);
                statement.setInt(3, id);
                statement.executeUpdate();
                conection.commit();
                judgement = true;
                var14 = false;
                break label66;
            } catch (Exception var16) {
                try {
                    conection.rollback();
                } catch (SQLException var15) {
                    var15.printStackTrace();
                    judgement = false;
                }

                var16.printStackTrace();
                judgement = false;
                var14 = false;
            } finally {
                if (var14) {
                    CloseJDBCUtils var10 = new CloseJDBCUtils();
                    var10.closeAll(conection, statement, (ResultSet) null);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
            return judgement;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
        return judgement;
    }

    @Override
    public boolean PublishedInfoDao(String infoTopic, String infoValue, Integer userId, Timestamp createdTime, Timestamp updatedTime) {
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        boolean var15 = false;

        boolean judgement;
        CloseJDBCUtils closeJDBCUtils;
        label66:
        {
            try {
                var15 = true;
                conection.setAutoCommit(false);
                String sql = "INSERT INTO t_info VALUES(NULL,?,?,?,?,?,?)";
                statement = conection.prepareStatement(sql);
                statement.setString(1, infoTopic);
                statement.setString(2, infoValue);
                statement.setInt(3, userId);
                statement.setString(4, "N");
                statement.setTimestamp(5, createdTime);
                statement.setTimestamp(6, updatedTime);
                statement.executeUpdate();
                conection.commit();
                judgement = true;
                var15 = false;
                break label66;
            } catch (Exception var17) {
                try {
                    conection.rollback();
                } catch (SQLException var16) {
                    var16.printStackTrace();
                    judgement = false;
                }

                var17.printStackTrace();
                judgement = false;
                var15 = false;
            } finally {
                if (var15) {
                    CloseJDBCUtils var11 = new CloseJDBCUtils();
                    var11.closeAll(conection, statement, (ResultSet) null);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
            return judgement;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
        return judgement;
    }

    @Override
    public ArrayList<HashMap<String, String>> TopicListDao() {
        User user = new User();
        Info info = new Info();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean var12 = false;

        CloseJDBCUtils closeJDBCUtils;
        label67:
        {
            try {
                var12 = true;
                String sql = "SELECT i.info_id,i.info_topic,i.is_delete,u.user_name,i.CREATED_TIME,i.UPDATED_TIME FROM t_info AS i INNER JOIN t_user AS u ON i.user_id = u.user_id";
                statement = conection.prepareStatement(sql);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    info.setInfoId(resultSet.getInt("i.info_id"));
                    info.setInfoTopic(resultSet.getString("i.info_topic"));
                    info.setIsDelete(resultSet.getString("i.is_delete"));
                    user.setName(resultSet.getString("u.user_name"));
                    info.setCreatedTime(resultSet.getTimestamp("i.CREATED_TIME"));
                    info.setUpdatedTime(resultSet.getTimestamp("i.UPDATED_TIME"));
                    if (info.getIsDelete().equals("N")) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("infoId", String.valueOf(info.getInfoId()));
                        map.put("infoTopic", info.getInfoTopic());
                        map.put("userName", user.getName());
                        map.put("create", String.valueOf(info.getCreatedTime()));
                        map.put("update", String.valueOf(info.getUpdatedTime()));
                        list.add(map);
                    }
                }

                var12 = false;
                break label67;
            } catch (Exception var13) {
                var13.printStackTrace();
                var12 = false;
            } finally {
                if (var12) {
                    CloseJDBCUtils var9 = new CloseJDBCUtils();
                    var9.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return list;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return list;
    }

    @Override
    public HashMap<String, String> ValueListDao(Integer infoId) {
        User user = new User();
        Info info = new Info();
        HashMap<String, String> map = new HashMap<>();
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean var12 = false;

        CloseJDBCUtils closeJDBCUtils;
        label67:
        {
            try {
                var12 = true;
                String sql = "SELECT i.info_id,i.info_topic,i.info_value,i.is_delete,u.user_name,u.user_intro,i.CREATED_TIME,i.UPDATED_TIME FROM t_info AS i INNER JOIN t_user AS u ON i.user_id = u.user_id WHERE i.info_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setInt(1, infoId);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    info.setInfoId(resultSet.getInt("i.info_id"));
                    info.setInfoTopic(resultSet.getString("i.info_topic"));
                    info.setInfoValue(resultSet.getString("i.info_value"));
                    info.setIsDelete(resultSet.getString("i.is_delete"));
                    user.setName(resultSet.getString("u.user_name"));
                    user.setIntro(resultSet.getString("u.user_intro"));
                    info.setCreatedTime(resultSet.getTimestamp("i.CREATED_TIME"));
                    info.setUpdatedTime(resultSet.getTimestamp("i.UPDATED_TIME"));
                    if (info.getIsDelete().equals("N")) {
                        map.put("infoId", String.valueOf(info.getInfoId()));
                        map.put("infoTopic", info.getInfoTopic());
                        map.put("infoValue", info.getInfoValue());
                        map.put("userName", user.getName());
                        map.put("userIntro", user.getIntro());
                        map.put("create", String.valueOf(info.getCreatedTime()));
                        map.put("update", String.valueOf(info.getUpdatedTime()));
                    }
                }

                var12 = false;
                break label67;
            } catch (Exception var13) {
                var13.printStackTrace();
                var12 = false;
            } finally {
                if (var12) {
                    CloseJDBCUtils var9 = new CloseJDBCUtils();
                    var9.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return map;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return map;
    }

    @Override
    public boolean ReviewDao(Integer userId, String commentsValue, Integer infoId, Integer secretsId, Timestamp create) {
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        boolean var15 = false;

        boolean judgement;
        CloseJDBCUtils closeJDBCUtils;
        label66:
        {
            try {
                var15 = true;
                conection.setAutoCommit(false);
                String sql = "INSERT INTO t_comments VALUES(NULL,?,?,?,?,'N',?,?)";
                statement = conection.prepareStatement(sql);
                statement.setInt(1, userId);
                statement.setString(2, commentsValue);
                if (infoId != null) {
                    statement.setInt(3, infoId);
                } else {
                    statement.setString(3, null);
                }
                if (secretsId != null) {
                    statement.setInt(4, secretsId);
                } else {
                    statement.setString(4, null);
                }
                statement.setTimestamp(5, create);
                statement.setTimestamp(6, create);
                statement.executeUpdate();
                conection.commit();
                judgement = true;
                var15 = false;
                break label66;
            } catch (Exception var17) {
                try {
                    conection.rollback();
                } catch (SQLException var16) {
                    var16.printStackTrace();
                    judgement = false;
                }

                var17.printStackTrace();
                judgement = false;
                var15 = false;
            } finally {
                if (var15) {
                    CloseJDBCUtils var11 = new CloseJDBCUtils();
                    var11.closeAll(conection, statement, (ResultSet) null);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
            return judgement;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
        return judgement;
    }

    @Override
    public ArrayList<HashMap<String, String>> CommentsValueListDao(Integer infoId) {
        User user = new User();
        Comments comments = new Comments();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean var12 = false;

        CloseJDBCUtils closeJDBCUtils;
        label67:
        {
            try {
                var12 = true;
                String sql = "SELECT c.comments_id,u.user_name,c.comments_value,c.is_delete,c.CREATED_TIME,c.UPDATED_TIME FROM t_comments AS c INNER JOIN t_user AS u ON c.user_id = u.user_id WHERE info_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setInt(1, infoId);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    comments.setCommentsId(resultSet.getInt("c.comments_id"));
                    user.setName(resultSet.getString("u.user_name"));
                    comments.setCommentsValue(resultSet.getString("c.comments_value"));
                    comments.setIsDelete(resultSet.getString("c.is_delete"));
                    comments.setCreatedTime(resultSet.getTimestamp("c.CREATED_TIME"));
                    comments.setUpdatedTime(resultSet.getTimestamp("c.UPDATED_TIME"));
                    if (comments.getIsDelete().equals("N")) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("commentsId", String.valueOf(comments.getCommentsId()));
                        map.put("userName", user.getName());
                        map.put("commentsValue", comments.getCommentsValue());
                        map.put("create", String.valueOf(comments.getCreatedTime()));
                        map.put("update", String.valueOf(comments.getUpdatedTime()));
                        list.add(map);
                    }
                }

                var12 = false;
                break label67;
            } catch (Exception var13) {
                var13.printStackTrace();
                var12 = false;
            } finally {
                if (var12) {
                    CloseJDBCUtils var9 = new CloseJDBCUtils();
                    var9.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return list;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return list;
    }

    @Override
    public Integer KeyGenerationDao(Integer id, Timestamp create, String privateKey, String publicKey) {
        int StatusNumber = 0;
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        CallableStatement statement = null;
        ResultSet resultSet = null;
        boolean var14 = false;

        CloseJDBCUtils closeJDBCUtils;
        label69:
        {
            try {
                var14 = true;
                String sql = "{CALL keyGeneration(?,?,?,?,?)}";
                statement = conection.prepareCall(sql);
                statement.setString(1, privateKey);
                statement.setString(2, publicKey);
                statement.setInt(3, id);
                statement.setTimestamp(4, create);
                statement.setTimestamp(5, create);

                for (resultSet = statement.executeQuery(); resultSet.next(); StatusNumber = resultSet.getInt("outk")) {
                }

                var14 = false;
                break label69;
            } catch (Exception var15) {
                var15.printStackTrace();
                var14 = false;
            } finally {
                if (var14) {
                    CloseJDBCUtils var11 = new CloseJDBCUtils();
                    var11.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return StatusNumber;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return StatusNumber;
    }

    @Override
    public Code GainCodeDao(Integer userId) {
        Code code = new Code();
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean var12 = false;

        CloseJDBCUtils closeJDBCUtils;
        label67:
        {
            try {
                var12 = true;
                String sql = "SELECT code_messages,code_privatekey,code_publickey,is_delete,CREATED_TIME,UPDATED_TIME FROM t_code WHERE user_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setInt(1, userId);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    code.setCodePrivatekey(resultSet.getString("code_privatekey"));
                    code.setCodePublickey(resultSet.getString("code_publickey"));
                    code.setIsDelete(resultSet.getString("is_delete"));
                    code.setCreatedTime(resultSet.getTimestamp("CREATED_TIME"));
                    code.setUpdatedTime(resultSet.getTimestamp("UPDATED_TIME"));
                }

                var12 = false;
                break label67;
            } catch (Exception var13) {
                var13.printStackTrace();
                var12 = false;
            } finally {
                if (var12) {
                    CloseJDBCUtils var9 = new CloseJDBCUtils();
                    var9.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return code;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return code;
    }

    @Override
    public boolean DeleteCodeDao(Integer userId) {
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        boolean var14 = false;

        CloseJDBCUtils closeJDBCUtils;
        boolean judgement;
        label66:
        {
            try {
                var14 = true;
                conection.setAutoCommit(false);
                String sql = "UPDATE t_code SET is_delete = 'Y' WHERE user_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setInt(1, userId);
                statement.executeUpdate();
                conection.commit();
                judgement = true;
                var14 = false;
                break label66;
            } catch (Exception var16) {
                try {
                    conection.rollback();
                } catch (SQLException var15) {
                    var15.printStackTrace();
                    judgement = false;
                }

                var16.printStackTrace();
                judgement = false;
                var14 = false;
            } finally {
                if (var14) {
                    CloseJDBCUtils var10 = new CloseJDBCUtils();
                    var10.closeAll(conection, statement, (ResultSet) null);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
            return judgement;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
        return judgement;
    }

    @Override
    public boolean SecretsDao(String topic, String signature, String value, Integer userId, Timestamp create) {
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        boolean var15 = false;

        boolean judgement;
        CloseJDBCUtils closeJDBCUtils;
        label66:
        {
            try {
                var15 = true;
                conection.setAutoCommit(false);
                String sql = "INSERT INTO t_secrets VALUES(NULL,?,?,?,?,?,?,?)";
                statement = conection.prepareStatement(sql);
                statement.setString(1, topic);
                statement.setString(2, signature);
                statement.setString(3, value);
                statement.setInt(4, userId);
                statement.setString(5, "N");
                statement.setTimestamp(6, create);
                statement.setTimestamp(7, create);
                statement.executeUpdate();
                conection.commit();
                judgement = true;
                var15 = false;
                break label66;
            } catch (Exception var17) {
                try {
                    conection.rollback();
                } catch (SQLException var16) {
                    var16.printStackTrace();
                    judgement = false;
                }

                var17.printStackTrace();
                judgement = false;
                var15 = false;
            } finally {
                if (var15) {
                    CloseJDBCUtils var11 = new CloseJDBCUtils();
                    var11.closeAll(conection, statement, (ResultSet) null);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
            return judgement;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
        return judgement;
    }

    @Override
    public Secrets TakeOutSecretsDao(Integer id) {
        Secrets secrets = new Secrets();
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean var12 = false;

        CloseJDBCUtils closeJDBCUtils;
        label67:
        {
            try {
                var12 = true;
                String sql = "SELECT secrets_topic,user_id,is_delete FROM t_secrets WHERE secrets_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    secrets.setSecretsTopic(resultSet.getString("secrets_topic"));
                    secrets.setUserId(resultSet.getInt("user_id"));
                    secrets.setIsDelete(resultSet.getString("is_delete"));
                }

                var12 = false;
                break label67;
            } catch (Exception var13) {
                var13.printStackTrace();
                var12 = false;
            } finally {
                if (var12) {
                    CloseJDBCUtils var9 = new CloseJDBCUtils();
                    var9.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return secrets;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return secrets;
    }

    @Override
    public Secrets CaptureDao(Integer secretsId) {
        Secrets secrets = new Secrets();
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean var12 = false;

        CloseJDBCUtils closeJDBCUtils;
        label67:
        {
            try {
                var12 = true;
                String sql = "SELECT secrets_id,secrets_topic,secrets_value,CREATED_TIME,UPDATED_TIME FROM t_secrets WHERE secrets_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setInt(1, secretsId);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    secrets.setSecretsId(resultSet.getInt("secrets_id"));
                    secrets.setSecretsTopic(resultSet.getString("secrets_topic"));
                    secrets.setSecretsValue(resultSet.getString("secrets_value"));
                    secrets.setCreatedTime(resultSet.getTimestamp("CREATED_TIME"));
                    secrets.setUpdatedTime(resultSet.getTimestamp("UPDATED_TIME"));
                }

                var12 = false;
                break label67;
            } catch (Exception var13) {
                var13.printStackTrace();
                var12 = false;
            } finally {
                if (var12) {
                    CloseJDBCUtils var9 = new CloseJDBCUtils();
                    var9.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return secrets;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return secrets;
    }

    @Override
    public ArrayList<HashMap<String, String>> CommentSecretListDao(Integer secretId) {
        Comments comments = new Comments();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean var12 = false;

        CloseJDBCUtils closeJDBCUtils;
        label67:
        {
            try {
                var12 = true;
                String sql = "SELECT comments_id,comments_value,is_delete,CREATED_TIME,UPDATED_TIME FROM t_comments WHERE secrets_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setInt(1, secretId);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    comments.setCommentsId(resultSet.getInt("comments_id"));
                    comments.setCommentsValue(resultSet.getString("comments_value"));
                    comments.setIsDelete(resultSet.getString("is_delete"));
                    comments.setCreatedTime(resultSet.getTimestamp("CREATED_TIME"));
                    comments.setUpdatedTime(resultSet.getTimestamp("UPDATED_TIME"));
                    if (comments.getIsDelete().equals("N")) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("commentsId", String.valueOf(comments.getCommentsId()));
                        map.put("commentsValue", comments.getCommentsValue());
                        map.put("create", String.valueOf(comments.getCreatedTime()));
                        map.put("update", String.valueOf(comments.getUpdatedTime()));
                        list.add(map);
                    }
                }

                var12 = false;
                break label67;
            } catch (Exception var13) {
                var13.printStackTrace();
                var12 = false;
            } finally {
                if (var12) {
                    CloseJDBCUtils var9 = new CloseJDBCUtils();
                    var9.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return list;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return list;
    }

    @Override
    public ArrayList<HashMap<String, String>> ManagementInfoDao(Integer userId) {
        Info info = new Info();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean var12 = false;

        CloseJDBCUtils closeJDBCUtils;
        label67:
        {
            try {
                var12 = true;
                String sql = "SELECT info_id,info_topic,info_value,is_delete,CREATED_TIME,UPDATED_TIME FROM t_info WHERE user_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setInt(1, userId);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    info.setInfoId(resultSet.getInt("info_id"));
                    info.setInfoTopic(resultSet.getString("info_topic"));
                    info.setInfoValue(resultSet.getString("info_value"));
                    info.setIsDelete(resultSet.getString("is_delete"));
                    info.setCreatedTime(resultSet.getTimestamp("CREATED_TIME"));
                    info.setUpdatedTime(resultSet.getTimestamp("UPDATED_TIME"));
                    if (info.getIsDelete().equals("N")) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("info_id", String.valueOf(info.getInfoId()));
                        map.put("info_topic", info.getInfoTopic());
                        map.put("info_value", info.getInfoValue());
                        map.put("create", String.valueOf(info.getCreatedTime()));
                        map.put("update", String.valueOf(info.getUpdatedTime()));
                        list.add(map);
                    }
                }

                var12 = false;
                break label67;
            } catch (Exception var13) {
                var13.printStackTrace();
                var12 = false;
            } finally {
                if (var12) {
                    CloseJDBCUtils var9 = new CloseJDBCUtils();
                    var9.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return list;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return list;
    }

    @Override
    public ArrayList<HashMap<String, String>> ManagementSecretDao(Integer userId) {
        Secrets secrets = new Secrets();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean var12 = false;

        CloseJDBCUtils closeJDBCUtils;
        label67:
        {
            try {
                var12 = true;
                String sql = "SELECT secrets_id,secrets_topic,secrets_signature,secrets_value,is_delete,CREATED_TIME,UPDATED_TIME FROM t_secrets WHERE user_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setInt(1, userId);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    secrets.setSecretsId(resultSet.getInt("secrets_id"));
                    secrets.setSecretsTopic(resultSet.getString("secrets_topic"));
                    secrets.setSecretsSignature(resultSet.getString("secrets_signature"));
                    secrets.setSecretsValue(resultSet.getString("secrets_value"));
                    secrets.setIsDelete(resultSet.getString("is_delete"));
                    secrets.setCreatedTime(resultSet.getTimestamp("CREATED_TIME"));
                    secrets.setUpdatedTime(resultSet.getTimestamp("UPDATED_TIME"));
                    if (secrets.getIsDelete().equals("N")) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("secrets_id", String.valueOf(secrets.getSecretsId()));
                        map.put("secrets_topic", secrets.getSecretsTopic());
                        map.put("secrets_value", secrets.getSecretsValue());
                        map.put("secrets_signature", secrets.getSecretsSignature());
                        map.put("create", String.valueOf(secrets.getCreatedTime()));
                        map.put("update", String.valueOf(secrets.getUpdatedTime()));
                        list.add(map);
                    }
                }

                var12 = false;
                break label67;
            } catch (Exception var13) {
                var13.printStackTrace();
                var12 = false;
            } finally {
                if (var12) {
                    CloseJDBCUtils var9 = new CloseJDBCUtils();
                    var9.closeAll(conection, statement, resultSet);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, resultSet);
            return list;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, resultSet);
        return list;
    }

    @Override
    public boolean ManagementDeleteSecretDao(Integer userId, Integer secretId) {
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        boolean var14 = false;

        CloseJDBCUtils closeJDBCUtils;
        boolean judgement;
        label66:
        {
            try {
                var14 = true;
                conection.setAutoCommit(false);
                String sql = "UPDATE t_secrets SET is_delete = 'Y' WHERE user_id = ? AND secrets_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setInt(1, userId);
                statement.setInt(2, secretId);
                statement.executeUpdate();
                conection.commit();
                judgement = true;
                var14 = false;
                break label66;
            } catch (Exception var16) {
                try {
                    conection.rollback();
                } catch (SQLException var15) {
                    var15.printStackTrace();
                    judgement = false;
                }

                var16.printStackTrace();
                judgement = false;
                var14 = false;
            } finally {
                if (var14) {
                    CloseJDBCUtils var10 = new CloseJDBCUtils();
                    var10.closeAll(conection, statement, (ResultSet) null);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
            return judgement;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
        return judgement;
    }

    @Override
    public boolean ManagementDeleteInfoDao(Integer userId, Integer infoId) {
        OpenJDBCUtils openJDBCUtils = new OpenJDBCUtils();
        Connection conection = openJDBCUtils.getConection();
        PreparedStatement statement = null;
        boolean var14 = false;

        CloseJDBCUtils closeJDBCUtils;
        boolean judgement;
        label66:
        {
            try {
                var14 = true;
                conection.setAutoCommit(false);
                String sql = "UPDATE t_info SET is_delete = 'Y' WHERE user_id = ? AND info_id = ?";
                statement = conection.prepareStatement(sql);
                statement.setInt(1, userId);
                statement.setInt(2, infoId);
                statement.executeUpdate();
                conection.commit();
                judgement = true;
                var14 = false;
                break label66;
            } catch (Exception var16) {
                try {
                    conection.rollback();
                } catch (SQLException var15) {
                    var15.printStackTrace();
                    judgement = false;
                }

                var16.printStackTrace();
                judgement = false;
                var14 = false;
            } finally {
                if (var14) {
                    CloseJDBCUtils var10 = new CloseJDBCUtils();
                    var10.closeAll(conection, statement, (ResultSet) null);
                }
            }

            closeJDBCUtils = new CloseJDBCUtils();
            closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
            return judgement;
        }

        closeJDBCUtils = new CloseJDBCUtils();
        closeJDBCUtils.closeAll(conection, statement, (ResultSet) null);
        return judgement;
    }
}

