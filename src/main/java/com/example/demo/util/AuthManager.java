package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author muzhenhua
 * @create 2018-08-27 13:53
 **/
public class AuthManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthManager.class);
    public static JSONObject getUserInfoByCode(String url, String code) {
        Map<String, Object> params = new HashMap<>();
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("code", code);

        params.put("queryMap", queryMap);
        LOGGER.info("UserController getAccessToken 开始调用接口,参数params:{}", JSON.toJSONString(params));
        String result = HttpClientUtils.doGetHttp(url, params);
        LOGGER.info("UserController getAccessToken 调用成功,返回值{}", result);
        System.err.println(result);
        JSONObject analyResult = JSONObject.parseObject(result);
        if (analyResult == null) {
            LOGGER.info("UserController getAccessToken 接口调用失败");
            return null;
        }
        return analyResult;
    }

    public static JSONObject getUserInfoByCode1(String url,String code) {
        Map<String, Object> params = new HashMap<>();
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("grant_code", code);
        queryMap.put("client_id","265");
        queryMap.put("grant_type","authorization_code");

        long time = new Date().getTime();
        String info="265:e191d074fc374a99b773b24a13547b88:"+time;
        String aeskey="e191d074fc374a99b773b24a13547b88";
        //String encryptWithAES = CryptoClient.encryptWithAES(info, aeskey);
        //queryMap.put("auth_token",encryptWithAES);
        params.put("queryMap", queryMap);


        LOGGER.info("UserController getAccessToken 开始调用接口,参数params:{}", JSON.toJSONString(params));
        String result = HttpClientUtils.doGetHttp(url, params);
        LOGGER.info("UserController getAccessToken 调用成功,返回值{}", result);
        System.err.println(result);
        JSONObject analyResult = JSONObject.parseObject(result);
        if (analyResult == null) {
            LOGGER.info("UserController getAccessToken 接口调用失败");
            return null;
        }
        return analyResult;
    }

    public static JSONObject getUserInfoByCode2(String url, String code,String clientId,String clientSecret,String redirectUri) {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("clientId",clientId);
        if(StringUtils.isNotBlank(clientId)){
            queryMap.put("clientId", clientId);
        }
        queryMap.put("grantType","authorizationCode");
        queryMap.put("code",code);
        queryMap.put("redirectUri",redirectUri);
        queryMap.put("clientSecret",clientSecret);

        String signatureHttpParams =CryptoClient.getSign(queryMap);  //眼前加密 TODO
        queryMap.put("sign",signatureHttpParams);
        params.put("queryMap", queryMap);

        LOGGER.info("UserController getAccessToken 开始调用接口,参数params:{}", JSON.toJSONString(params));
        String result = HttpClientUtils.doGetHttp(url, params);
        LOGGER.info("UserController getAccessToken 调用成功,返回值{}", result);
        System.err.println(result);
        JSONObject analyResult = JSONObject.parseObject(result);
        if (analyResult == null) {
            LOGGER.info("UserController getAccessToken 接口调用失败");
            return null;
        }
        return analyResult;
    }

    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        Map<String, Object> bizParams = new HashMap<>();

        queryMap.put("appId","20180123640464");
        queryMap.put("timestamp",System.currentTimeMillis());
        queryMap.put("version","1.0");
        queryMap.put("toonType","104");
        queryMap.put("signType","MD5");
        queryMap.put("appSecret","8f2a3346c4b2348f8032be26c55320a4");
        bizParams.put("mobiles","15210670253");
        bizParams.put("toonType","104");
        bizParams.put("version","1.0");
        bizParams.put("platform","web");
        bizParams.put("ip","127.0.0.1");
        queryMap.put("bizParams",JSONObject.toJSON(bizParams));
        String signatureHttpParams =CryptoClient.getSign(queryMap);  //眼前加密 TODO
        queryMap.put("sign",signatureHttpParams);

        params.put("queryMap", queryMap);
        System.out.println(JSONObject.toJSON(params));
        LOGGER.info("UserController getAccessToken 开始调用接口,参数params:{}", JSON.toJSONString(params));
        String result = HttpClientUtils.doGetHttp("https://user.systoon.com/api/np/batchGetUserInfoByMobiles", params);
        LOGGER.info("UserController getAccessToken 调用成功,返回值{}", result);
        System.err.println(result);
        JSONObject analyResult = JSONObject.parseObject(result);
    }

    //根据用户信息获得
    public static  JSONObject getUserInfo(String url,String accessToken) {
        Map<String, Object> params = new HashMap<>();
        Map<String, String> headerMap = new HashMap<>();
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("accessToken", accessToken);
        headerMap.put("X-Access-Token", accessToken);

        params.put("headerMap", headerMap);
        params.put("queryMap", queryMap);
        LOGGER.info("UserController getUserInfo 调用组织平台获取用户信息,参数params:{}", JSON.toJSONString(params));
        JSONObject analyResult=null;
        try {
            String getUserInfo = HttpClientUtils.doPostHttp(url,params);
            LOGGER.info("UserController getUserInfo 调用接口 返回值getUserInfo:{}", getUserInfo);
            analyResult = JSONObject.parseObject(getUserInfo);
            if (analyResult == null) {
                LOGGER.info("UserController getUserInfo 调用接口失败 ");
                return null;
            }
            return analyResult;
        } catch (Exception e) {
            LOGGER.error("UserController getUserInfo 用户数据存入本地数据库失败", e);
            return null;
        }
    }

    public static Boolean chechSessionId(String url,String gSessionId){

        Map<String, Object> params = new HashMap<>();
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("gSessionId",gSessionId);
        params.put("queryMap", queryMap);
        String result = HttpClientUtils.doGetHttp(url, params);
        JSONObject json=JSONObject.parseObject(result);
        if("0".equals(json.getString("code"))){
            return json.getBoolean("data");
        }
        return true;
    }
    /**
     * 解析返回结果
     * @param result
     * @return
     */
    private JSONObject analyResult(String result) {
        if (StringUtils.isBlank(result)) {
            LOGGER.info("UserController analyResult 参数为空");
            return null;
        }
        JSONObject parseObject = JSON.parseObject(result);
        if (parseObject.getString("code").equals("0")) {
            JSONObject data = parseObject.getJSONObject("data");
            return data;
        }else {
            LOGGER.info("UserController analyResult 调用接口失败,message{}", parseObject.getString("message"));
            return null;
        }
    }
}
