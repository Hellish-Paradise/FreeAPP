package com.free.FreeAPP.dao;

/**
 * @Date 2021/10/18   9:05
 */

import com.free.FreeAPP.model.Code;
import com.free.FreeAPP.model.Secrets;
import com.free.FreeAPP.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public interface IDao {
    default boolean RegisterDao(String name, String email, String password, String sex, Integer status, Timestamp create, Timestamp update) {
        return false;
    }

    default User LoginDao(String email, String password) {
        return null;
    }

    default boolean SessionDao(String id, Integer userId, String terminal, Timestamp create, Timestamp update) {
        return false;
    }

    default boolean LogoutDao(String id) {
        return false;
    }

    default User AuthDao(String id, String password) {
        return null;
    }

    default User UserAccessDao(Integer id) {
        return null;
    }

    default boolean NameUpdateDao(Integer id, String name, Timestamp update) {
        return false;
    }

    default boolean IntroUpdateDao(Integer id, String intro, Timestamp update) {
        return false;
    }

    default boolean EmailUpdateDao(Integer id, String email, Timestamp update) {
        return false;
    }

    default boolean SexUpdateDao(Integer id, String sex, Timestamp update) {
        return false;
    }

    default Integer PasswordUpdateDao(Integer id, String password, Timestamp update) {
        return null;
    }

    default User LoginAuthDao(String id) {
        return null;
    }

    default boolean DeregisterDao(Integer id, Timestamp update) {
        return false;
    }

    default boolean PublishedInfoDao(String infoTopic, String infoValue, Integer userId, Timestamp createdTime, Timestamp updatedTime) {
        return false;
    }

    default ArrayList<HashMap<String, String>> TopicListDao() {
        return null;
    }

    default HashMap<String, String> ValueListDao(Integer infoId) {
        return null;
    }

    default boolean ReviewDao(Integer userId, String commentsValue, Integer infoId, Integer secretsId, Timestamp create) {
        return false;
    }

    default ArrayList<HashMap<String, String>> CommentsValueListDao(Integer infoId) {
        return null;
    }

    default Integer KeyGenerationDao(Integer id, Timestamp create, String privateKey, String publicKey) {
        return null;
    }

    default Code GainCodeDao(Integer userId) {
        return null;
    }

    default boolean DeleteCodeDao(Integer userId) {
        return false;
    }

    default boolean SecretsDao(String topic, String signature, String value, Integer userId, Timestamp create) {
        return false;
    }

    default Secrets TakeOutSecretsDao(Integer id) {
        return null;
    }

    default Secrets CaptureDao(Integer secretsId) {
        return null;
    }

    default ArrayList<HashMap<String, String>> CommentSecretListDao(Integer secretId) {
        return null;
    }

    default ArrayList<HashMap<String, String>> ManagementInfoDao(Integer userId) {
        return null;
    }

    default ArrayList<HashMap<String, String>> ManagementSecretDao(Integer userId) {
        return null;
    }

    default boolean ManagementDeleteSecretDao(Integer userId, Integer secretId) {
        return false;
    }

    default boolean ManagementDeleteInfoDao(Integer userId, Integer infoId) {
        return false;
    }
}