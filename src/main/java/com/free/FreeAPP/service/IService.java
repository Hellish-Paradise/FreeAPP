package com.free.FreeAPP.service;

/**
 * @Date 2021/10/18   9:15
 */

import com.free.FreeAPP.model.Code;
import com.free.FreeAPP.model.Secrets;
import com.free.FreeAPP.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public interface IService {
    default boolean RegisterService(String name, String email, String password, String sex, Timestamp create, Timestamp update) {
        return false;
    }

    default User LoginService(String email, String password) {
        return null;
    }

    default boolean SessionService(String id, Integer userId, String terminal, Timestamp create, Timestamp update) {
        return false;
    }

    default boolean LogoutService(String id) {
        return false;
    }

    default User AuthService(String id, String password) {
        return null;
    }

    default User UserAccessService(Integer id) {
        return null;
    }

    default boolean NameUpdateService(Integer id, String name, Timestamp update) {
        return false;
    }

    default boolean IntroUpdateService(Integer id, String intro, Timestamp update) {
        return false;
    }

    default boolean EmailUpdateService(Integer id, String email, Timestamp update) {
        return false;
    }

    default boolean SexUpdateService(Integer id, String sex, Timestamp update) {
        return false;
    }

    default Integer PasswordUpdateService(Integer id, String password, Timestamp update) {
        return null;
    }

    default User LoginAuthService(String id) {
        return null;
    }

    default boolean DeregisterService(Integer id, Timestamp update) {
        return false;
    }

    default boolean PublishedInfoService(String infoTopic, String infoValue, Integer userId, Timestamp createdTime, Timestamp updatedTime) {
        return false;
    }

    default ArrayList<HashMap<String, String>> TopicListService() {
        return null;
    }

    default HashMap<String, String> ValueListService(Integer infoId) {
        return null;
    }

    default boolean ReviewService(Integer userId, String commentsValue, Integer infoId, Integer secretsId, Timestamp create) {
        return false;
    }

    default ArrayList<HashMap<String, String>> CommentsValueListService(Integer infoId) {
        return null;
    }

    default Integer KeyGenerationService(Integer id, Timestamp create) {
        return null;
    }

    default Code GainCodeService(Integer userId) {
        return null;
    }

    default boolean DeleteCodeService(Integer userId) {
        return false;
    }

    default boolean SecretsService(String topic, String codePrivatekey, String value, Integer userId, Timestamp create) {
        return false;
    }

    default Secrets TakeOutSecretsService(Integer id) {
        return null;
    }

    default Secrets CaptureService(Integer secretsId, String topic, String codePublickey, String signature) {
        return null;
    }

    default ArrayList<HashMap<String, String>> CommentSecretListService(Integer secretId) {
        return null;
    }

    default ArrayList<HashMap<String, String>> ManagementInfoService(Integer userId) {
        return null;
    }

    default ArrayList<HashMap<String, String>> ManagementSecretService(Integer userId) {
        return null;
    }

    default boolean ManagementDeleteSecretService(Integer userId, Integer secretId) {
        return false;
    }

    default boolean ManagementDeleteInfoService(Integer userId, Integer infoId) {
        return false;
    }
}
