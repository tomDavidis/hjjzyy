package com.example.demo.dd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.Content;
import com.example.demo.pojo.TextLink;
import com.example.demo.pojo.Title;
import com.example.demo.util.*;


import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @PROJECT_NAME: hs-access-hospital-senddata-standard
 * @PACKAGE_NAME: com.hundsun.med.hdp.service.pt
 * @CLASS_NAME: TestPush2
 * @CREATE_USER: 谢晗
 * @DATE: 2019/8/30
 * @TIME: 15:24
 * @Description: step Two 发送消息通知  （平台示例）
 **/
public class TestPush2 {

    public static void test() {
        NoticeModel noticeModel=new NoticeModel();
        noticeModel.setMsgId(UUID.randomUUID().toString());
        noticeModel.setToonType("104");//怀柔通使用104
        noticeModel.setUserId(18249373);
        NoticePushInfo noticePushInfo=new NoticePushInfo();
        noticePushInfo.setPassThrough("0");
        noticePushInfo.setDescription("系统维护通知");
        noticePushInfo.setPayload("系统维护");
        noticePushInfo.setTitle("系统维护");
//        noticePushInfo.setDescription("气象预警通知");
//        noticePushInfo.setPayload("气象预警通知");
//        noticePushInfo.setTitle("气象预警");
        noticeModel.setNoticePushInfo(noticePushInfo);
        NoticeInfo noticeInfo=new NoticeInfo();

        Content content=new Content();
        content.setText("尊敬的怀柔通用户您好：\r\n怀柔通拟于2019年6月16日-6月17日进行系统维护升级，升级造成部分服务暂不可用，给您带来不便敬请谅解!" );
        content.setColor("");
        Title title=new Title();
        title.setText("系统维护30");
        //如果需要详情连接。则需要如下的对接参数
        Operation operation=new Operation();
        List<TextLink> textLinks=new ArrayList<>();
        TextLink textLink=new TextLink();
        textLink.setColor("");
        textLink.setHref("http://www.rihangkeji.com/wap/warnyb.html?number=2019046&productId=2019-06-04%2015:45:00_BJ-HRO_2019046&time=2019-06-04%2015:58:58");
        textLink.setText("详情");
        textLinks.add(textLink);
        //operation.setTextLinks(textLinks);
        //noticeInfo.setOperation(operation);

        noticeInfo.setTitle(title);
        noticeInfo.setContent(content);
        noticeModel.setNoticeInfo(noticeInfo);

        Map<String,String> headerMap=new HashMap<>();
        //TODO 这个是appkey:appSecret的base64加密过得
        //   AppKey ac4ddd3707
        //   Secret  f23408dac3284126be0f738d098d58c6
        String appKey="3f5287c875";
        String appSecret="608633dfc8014dc79618ccb29d0a3438";
//        String appKey="5c6c83a494";
//        String appSecret="34b289acb9aa436890f38f8c75053f60";
        String authorization1=appKey+":"+appSecret;

        byte[] bytes = new byte[1024];
        try {
            bytes = authorization1.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String authorization= Base64.getEncoder().encodeToString(bytes);
        System.out.println(JSONObject.toJSONString(noticeModel));
        headerMap.put("authorization",authorization);
        String doPostJson = HttpClientUtils.doPostJson("http://noticegateway.huairoutoon.com/open/sendNotice", JSON.toJSONString(noticeModel), headerMap);
        System.out.println(doPostJson);
    }
}
