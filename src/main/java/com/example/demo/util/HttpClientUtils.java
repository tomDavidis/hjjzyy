package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.*;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * http请求工具类
 * @author 申志峰<br/>
 * @使用方法<br/> 在使用时必须严格命名参数map：<br/>
 * queryMap：一般的查询参数<br/>
 * headerMap：需要放在header中的参数，比如accessToken<br/>
 * bodyMap：post请求中的参数一般放在body中<br/>
 */
@SuppressWarnings("unchecked")
public class HttpClientUtils implements Serializable{
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	private static final long serialVersionUID = 3954337501397850688L;
	private static final int NOT_MODIFIED = 304;// Not Modified: There was no new data to return.
	private static final int BAD_REQUEST = 400;// Bad Request: The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.
	private static final int NOT_AUTHORIZED = 401;// Not Authorized: Authentication credentials were missing or incorrect.
	private static final int FORBIDDEN = 403;// Forbidden: The request is understood, but it has been refused.  An accompanying error message will explain why.
	private static final int NOT_FOUND = 404;// Not Found: The URI requested is invalid or the resource requested, such as a user, does not exists.
	private static final int NOT_ACCEPTABLE = 406;// Not Acceptable: Returned by the Search API when an invalid format is specified in the request.
	private static final int INTERNAL_SERVER_ERROR = 500;// Internal Server Error: Something is broken.  Please post to the group so the Weibo team can investigate.
	private static final int BAD_GATEWAY = 502;// Bad Gateway: Weibo is down or being upgraded.
	private static final int SERVICE_UNAVAILABLE = 503;// Service Unavailable: The Weibo servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.
	private static final String CHARSET = "UTF-8";

	/**
	 * 以post方式发送http请求
	 *
	 * @param params
	 * @param url
	 * @return
	 */
	public static String doPostHttp(String url, Map<String, Object> params) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		Map<String, Object> queryMap = null;
		if (null != params.get("queryMap")) {
			queryMap = (Map<String, Object>) params.get("queryMap");
		}

		String fullUrl = new String(url);
		if (null != queryMap && queryMap.size() > 0) {
			List<NameValuePair> pairs = mapToList(queryMap);
			if (pairs != null && queryMap.size() > 0) {
				String label = "?";
				if (url.contains("?"))
					label = "&";
				try {
					fullUrl += label + EntityUtils.toString(new UrlEncodedFormEntity(pairs, CHARSET));
				} catch (ParseException | IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		HttpPost httpPost = new HttpPost(fullUrl);

		Map<String, String> headerMap = null;
		if (null != params.get("headerMap")) {
			headerMap = (Map<String, String>) params.get("headerMap");
		}
		if (null != headerMap && headerMap.size() > 0) {
			Set<String> set = headerMap.keySet();
			if (null != set && set.size() > 0) {
				for (String key : set) {
					httpPost.addHeader(key, headerMap.get(key));
				}
			}
		}
		Map<String, String> bodyMap = null;
		if (null != params.get("bodyMap")) {
			bodyMap = (Map<String, String>) params.get("bodyMap");
		}
		try {
			String bodyContent = "";
			if (null != bodyMap && bodyMap.size() > 0) {
                /*Set<String> set = bodyMap.keySet();
				if (null != set && set.size() > 0) {
					for (String key : set) {
						bodyContent = bodyMap.get(key);
					}
				}*/
				bodyContent = JSON.toJSONString(bodyMap);
			}

			StringEntity entity = new StringEntity(bodyContent);
			entity.setContentEncoding(CHARSET);
			entity.setContentType("application/json");// 发送json数据需要设置contentType

			httpPost.setEntity(entity);

			// 创建响应控制器
			ResponseHandler<HttpEntity> handler = createResponseHandler(url);

			HttpEntity httpEntity = httpclient.execute(httpPost, handler);
			//将结果转化为json返回
			String result = EntityUtils.toString(httpEntity, "UTF-8");
			EntityUtils.consume(httpEntity);
			return result;
		} catch (Exception e) {
			logger.error("post请求失败! error: ", e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("==HttpClientUtils.doPostHttp(" + url,
				        params + ")==exception==", e);
			}
		}
		return null;
	}
	/**
	 * 以get方式发送http请求
	 *
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doGetHttp(final String url, Map<String, Object> params) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		String fullUrl = new String(url);
		Map<String, Object> queryMap = null;
		if (null != params.get("queryMap")) {
			queryMap = (Map<String, Object>) params.get("queryMap");
		}
		try {
			if (null != queryMap && queryMap.size() > 0) {
				List<NameValuePair> pairs = mapToList(queryMap);
				if (pairs != null && queryMap.size() > 0) {
					String label = "?";
					if (url.contains("?"))
						label = "&";
					fullUrl += label + EntityUtils.toString(new UrlEncodedFormEntity(pairs, CHARSET));
				}
			}
			System.out.println("请求地址参数为："+fullUrl);
			HttpGet httpGet = new HttpGet(fullUrl);
			Map<String, String> headerMap = null;
			if (null != params.get("headerMap")) {
				headerMap = (Map<String, String>) params.get("headerMap");
			}
			if (null != headerMap && headerMap.size() > 0) {
				Set<String> set = headerMap.keySet();
				if (null != set && set.size() > 0) {
					for (String key : set) {
						httpGet.addHeader(key, headerMap.get(key));
					}
				}
			}

			// 创建响应控制器
			ResponseHandler<HttpEntity> handler = createResponseHandler(url);

			HttpEntity httpEntity = httpclient.execute(httpGet, handler);
			//以json格式返回结果
			String result = EntityUtils.toString(httpEntity, "UTF-8");
			EntityUtils.consume(httpEntity);
			return result;
		} catch (Exception e) {
			logger.error("get请求失败! error: ", e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("==HttpClientUtils.doGetHttp(" + url,
				        params + ")==exception==", e);
			}
		}
		return null;
	}

	public static List<NameValuePair> mapToList(Map<String, Object> params) {
		List<NameValuePair> pairs = null;

		if (params != null && !params.isEmpty()) {
			pairs = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				Object value = entry.getValue();
				if (value != null && value.toString().length() > 0) {
					pairs.add(new BasicNameValuePair(entry.getKey(), value.toString()));
				}
			}
		}
		return pairs;
	}

	/**
	 * 创建一个响应控制器
	 *
	 * @return 返回一个请求响应控制实例
	 */
	private static ResponseHandler<HttpEntity> createResponseHandler(final String url) {
		return new ResponseHandler<HttpEntity>() {
			public HttpEntity handleResponse(HttpResponse response) throws IOException {
				int status = response.getStatusLine().getStatusCode();
				if (status != HttpStatus.SC_OK) {
					try {
						throw new Exception("request [" + url + "] failed, detail: " + getCause(status));
					} catch (Exception e) {
						logger.error(
						        "==HttpClientUtils.createResponseHandler(...).new ResponseHandler() {...}.handleResponse("
						                + response + ")==exception==",
						        e);
					}
				}

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					return new BufferedHttpEntity(entity);
				} else {
					return null;
				}
			}
		};
	}


	/**
	 * 获取状态描述
	 *
	 * @param statusCode
	 * @return
	 */
	private static String getCause(int statusCode) {
		String cause = null;
		switch (statusCode) {
			case NOT_MODIFIED:
				break;
			case BAD_REQUEST:
				cause = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
				break;
			case NOT_AUTHORIZED:
				cause = "Authentication credentials were missing or incorrect.";
				break;
			case FORBIDDEN:
				cause = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
				break;
			case NOT_FOUND:
				cause = "The URI requested is invalid or the resource requested.";
				break;
			case NOT_ACCEPTABLE:
				cause = "Returned by the Search API when an invalid format is specified in the request.";
				break;
			case INTERNAL_SERVER_ERROR:
				cause = "Something is broken. Please post to the group so the Toon-Auth team can investigate.";
				break;
			case BAD_GATEWAY:
				cause = "Toon-Auth is down or being upgraded.";
				break;
			case SERVICE_UNAVAILABLE:
				cause = "Service Unavailable: The Toon-Auth servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
				break;
			default:
				cause = "";
		}
		return statusCode + ":" + cause;
	}
	
	
	/**
	 * 
	 * @author zhangjian
	 * @date 2017年12月28日 上午9:08:54 
	 * @param url:请求地址
	 * @param json:参数的json格式
	 * @param headerParams:请求头参数
	 * @return String
	 * @Description: TODO
	 */
	public static String doPostJson(String url, String json, Map<String, String> headerParams) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 创建hander参数列表
			if (headerParams != null) {
				Set<String> keySet = headerParams.keySet();
				if (keySet.size() != 0) {
					for (String string : keySet) {
						httpPost.setHeader(string, headerParams.get(string));
					}
				}
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultString;
	}
}
