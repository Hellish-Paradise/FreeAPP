package com.free.FreeAPP.service.impl;

/**
 * @Date 2021/10/18   9:14
 */

import com.free.FreeAPP.dao.impl.DaoImpl;
import com.free.FreeAPP.model.Code;
import com.free.FreeAPP.model.Secrets;
import com.free.FreeAPP.model.User;
import com.free.FreeAPP.model.enums.Sex;
import com.free.FreeAPP.model.enums.Status;
import com.free.FreeAPP.service.IService;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

public class ServiceImpl implements IService {
    public ServiceImpl() {
    }

    public boolean RegisterService(String name, String email, String password, String sex, Timestamp create, Timestamp update) {
        String pwd = null;
        String sexc = null;
        Integer status = Integer.valueOf(Status.Normal.toString());

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] result = md.digest();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(result);
            byte[] bytes = md5.digest();
            pwd = (new BigInteger(-1, bytes)).toString(36);
        } catch (NoSuchAlgorithmException var14) {
            var14.printStackTrace();
            return false;
        }

        byte var16 = -1;
        switch (sex.hashCode()) {
            case 22899:
                if (sex.equals("女")) {
                    var16 = 1;
                }
                break;
            case 30007:
                if (sex.equals("男")) {
                    var16 = 0;
                }
                break;
            case 657289:
                if (sex.equals("保密")) {
                    var16 = 2;
                }
        }

        switch (var16) {
            case 0:
                sexc = Sex.Male.toString();
                break;
            case 1:
                sexc = Sex.Female.toString();
                break;
            case 2:
                sexc = Sex.Confidentiality.toString();
        }

        DaoImpl dao = new DaoImpl();
        return dao.RegisterDao(name, email, pwd, sexc, status, create, update);
    }

    public User LoginService(String email, String password) {
        String pwd = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] result = md.digest();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(result);
            byte[] bytes = md5.digest();
            pwd = (new BigInteger(-1, bytes)).toString(36);
        } catch (NoSuchAlgorithmException var8) {
            var8.printStackTrace();
        }

        DaoImpl dao = new DaoImpl();
        return dao.LoginDao(email, pwd);
    }

    public boolean SessionService(String id, Integer userId, String terminal, Timestamp create, Timestamp update) {
        DaoImpl dao = new DaoImpl();
        return dao.SessionDao(id, userId, terminal, create, update);
    }

    public boolean LogoutService(String id) {
        DaoImpl dao = new DaoImpl();
        return dao.LogoutDao(id);
    }

    public User AuthService(String id, String password) {
        String pwd = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] result = md.digest();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(result);
            byte[] bytes = md5.digest();
            pwd = (new BigInteger(-1, bytes)).toString(36);
        } catch (NoSuchAlgorithmException var8) {
            var8.printStackTrace();
        }

        DaoImpl dao = new DaoImpl();
        return dao.AuthDao(id, pwd);
    }

    public User UserAccessService(Integer id) {
        DaoImpl dao = new DaoImpl();
        return dao.UserAccessDao(id);
    }

    public boolean NameUpdateService(Integer id, String name, Timestamp update) {
        DaoImpl dao = new DaoImpl();
        return dao.NameUpdateDao(id, name, update);
    }

    public boolean IntroUpdateService(Integer id, String intro, Timestamp update) {
        DaoImpl dao = new DaoImpl();
        return dao.IntroUpdateDao(id, intro, update);
    }

    public boolean EmailUpdateService(Integer id, String email, Timestamp update) {
        DaoImpl dao = new DaoImpl();
        return dao.EmailUpdateDao(id, email, update);
    }

    public boolean SexUpdateService(Integer id, String sex, Timestamp update) {
        String sexc = null;
        byte var6 = -1;
        switch (sex.hashCode()) {
            case 22899:
                if (sex.equals("女")) {
                    var6 = 1;
                }
                break;
            case 30007:
                if (sex.equals("男")) {
                    var6 = 0;
                }
                break;
            case 657289:
                if (sex.equals("保密")) {
                    var6 = 2;
                }
        }

        switch (var6) {
            case 0:
                sexc = Sex.Male.toString();
                break;
            case 1:
                sexc = Sex.Female.toString();
                break;
            case 2:
                sexc = Sex.Confidentiality.toString();
        }

        DaoImpl dao = new DaoImpl();
        return dao.SexUpdateDao(id, sexc, update);
    }

    public Integer PasswordUpdateService(Integer id, String password, Timestamp update) {
        String pwd = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] result = md.digest();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(result);
            byte[] bytes = md5.digest();
            pwd = (new BigInteger(-1, bytes)).toString(36);
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
        }

        DaoImpl dao = new DaoImpl();
        return dao.PasswordUpdateDao(id, pwd, update);
    }

    public User LoginAuthService(String id) {
        DaoImpl dao = new DaoImpl();
        return dao.LoginAuthDao(id);
    }

    public boolean DeregisterService(Integer id, Timestamp update) {
        DaoImpl dao = new DaoImpl();
        return dao.DeregisterDao(id, update);
    }

    @Override
    public boolean PublishedInfoService(String infoTopic, String infoValue, Integer userId, Timestamp createdTime, Timestamp updatedTime) {
        DaoImpl dao = new DaoImpl();
        return dao.PublishedInfoDao(infoTopic, infoValue, userId, createdTime, updatedTime);
    }

    @Override
    public ArrayList<HashMap<String, String>> TopicListService() {
        DaoImpl dao = new DaoImpl();
        return dao.TopicListDao();
    }

    @Override
    public HashMap<String, String> ValueListService(Integer infoId) {
        DaoImpl dao = new DaoImpl();
        return dao.ValueListDao(infoId);
    }

    @Override
    public boolean ReviewService(Integer userId, String commentsValue, Integer infoId, Integer secretsId, Timestamp create) {
        DaoImpl dao = new DaoImpl();
        return dao.ReviewDao(userId, commentsValue, infoId, secretsId, create);
    }

    @Override
    public ArrayList<HashMap<String, String>> CommentsValueListService(Integer infoId) {
        DaoImpl dao = new DaoImpl();
        return dao.CommentsValueListDao(infoId);
    }

    @Override
    public Integer KeyGenerationService(Integer id, Timestamp create) {
        DaoImpl dao = new DaoImpl();
        // 生成RSA公钥/私钥:
        KeyPairGenerator kpGen = null;
        try {
            kpGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert kpGen != null;
        kpGen.initialize(1024, new SecureRandom());
        KeyPair kp = kpGen.generateKeyPair();
        PrivateKey sk = kp.getPrivate();
        String encodeKeys = Base64.getMimeEncoder().encodeToString(sk.getEncoded());
        PublicKey pk = kp.getPublic();
        String encodeKeyp = Base64.getMimeEncoder().encodeToString(pk.getEncoded());
        return dao.KeyGenerationDao(id, create, encodeKeys, encodeKeyp);
    }

    @Override
    public Code GainCodeService(Integer userId) {
        DaoImpl dao = new DaoImpl();
        return dao.GainCodeDao(userId);
    }

    @Override
    public boolean DeleteCodeService(Integer userId) {
        DaoImpl dao = new DaoImpl();
        return dao.DeleteCodeDao(userId);
    }

    @Override
    public boolean SecretsService(String topic, String codePrivatekey, String value, Integer userId, Timestamp create) {
        DaoImpl dao = new DaoImpl();
        boolean flag;
        try {
            // 待签名的消息:
            byte[] message = topic.getBytes(StandardCharsets.UTF_8);
            byte[] decodeKeys = Base64.getMimeDecoder().decode(codePrivatekey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodeKeys);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            // 用私钥签名:
            Signature s = Signature.getInstance("SHA512withRSA");
            s.initSign(privateKey);
            s.update(message);
            byte[] signed = s.sign();
            String s1 = Base64.getMimeEncoder().encodeToString(signed);
            flag = dao.SecretsDao(topic, s1, value, userId, create);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public Secrets TakeOutSecretsService(Integer id) {
        DaoImpl dao = new DaoImpl();
        return dao.TakeOutSecretsDao(id);
    }

    @Override
    public Secrets CaptureService(Integer secretsId, String topic, String codePublickey, String signature) {
        DaoImpl dao = new DaoImpl();
        try {
            byte[] topicBytes = topic.getBytes(StandardCharsets.UTF_8);
            byte[] keyBytesp = Base64.getMimeDecoder().decode(codePublickey);
            X509EncodedKeySpec keySpecp = new X509EncodedKeySpec(keyBytesp);
            KeyFactory keyFactoryp = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactoryp.generatePublic(keySpecp);
            // 用公钥验证:
            byte[] decode = Base64.getMimeDecoder().decode(signature);
            Signature v = Signature.getInstance("SHA512withRSA");
            v.initVerify(publicKey);
            v.update(topicBytes);
            boolean valid = v.verify(decode);
            if (valid) {
                return dao.CaptureDao(secretsId);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<HashMap<String, String>> CommentSecretListService(Integer secretId) {
        DaoImpl dao = new DaoImpl();
        return dao.CommentSecretListDao(secretId);
    }

    @Override
    public ArrayList<HashMap<String, String>> ManagementInfoService(Integer userId) {
        DaoImpl dao = new DaoImpl();
        return dao.ManagementInfoDao(userId);
    }

    @Override
    public ArrayList<HashMap<String, String>> ManagementSecretService(Integer userId) {
        DaoImpl dao = new DaoImpl();
        return dao.ManagementSecretDao(userId);
    }

    @Override
    public boolean ManagementDeleteSecretService(Integer userId, Integer secretId) {
        DaoImpl dao = new DaoImpl();
        return dao.ManagementDeleteSecretDao(userId, secretId);
    }

    @Override
    public boolean ManagementDeleteInfoService(Integer userId, Integer infoId) {
        DaoImpl dao = new DaoImpl();
        return dao.ManagementDeleteInfoDao(userId, infoId);
    }
}
