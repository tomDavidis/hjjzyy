package com.example.demo.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;

import java.util.*;
import java.util.Map.Entry;

public class CryptoClient {
	/**
	 * 对请求参数进行签名
	 * 
	 * @param appSecret
	 *            应用秘钥
	 * @param inputParams
	 *            请求参数
	 * @return
	 */
	public static String signatureHttpParams(String appSecret, Map<String, String> inputParams) {
		StringBuilder paramStr = new StringBuilder();
		if (null != inputParams && !inputParams.isEmpty()) {
			for (Entry<String, String> paramKeyVal : inputParams.entrySet()) {
				paramStr.append(paramKeyVal.getKey()).append("=").append(paramKeyVal.getValue()).append("&");
			}
			paramStr.setLength(paramStr.length() - 1);
		}
		String signature = HmacUtils.hmacMd5Hex(appSecret, paramStr.toString()).toUpperCase();
		return signature;
	}
		/**
	 * 生成签名
	 * @param map
	 * @return
	 */
	public static String getSign(Map<String, Object> map) {
		String result = "";
		try {
			List<Entry<String, Object>> infoIds = new ArrayList<Entry<String, Object>>(map.entrySet());
			// 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
			Collections.sort(infoIds, new Comparator<Entry<String, Object>>() {

				public int compare(Entry<String, Object> o1, Entry<String, Object> o2) {
					return (o1.getKey()).toString().compareTo(o2.getKey());
				}
			});
			// 构造签名键值对的格式
			StringBuilder sb = new StringBuilder();
			for (Entry<String, Object> item : infoIds) {
				if (item.getKey() != null || item.getKey() != "") {
					String key = item.getKey();
					String val = item.getValue().toString();
					if (!(val == "" || val == null)) {
						sb.append(key + "=" + val+"&");
					}
				}
			}
//			sb.append(PropertyManager.getProperty("SIGNKEY"));
			result = sb.toString().substring(0,sb.toString().length()-1);
			System.out.println("排序后的串为："+result);
			//进行MD5加密
			result = DigestUtils.md5Hex(result).toUpperCase();
			System.out.println("加密后的串为："+result);
		} catch (Exception e) {
			return null;
		}
		return result;
	}

}
