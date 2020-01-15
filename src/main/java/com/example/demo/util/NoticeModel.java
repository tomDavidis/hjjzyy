package com.example.demo.util;

import java.io.Serializable;

/**
 * @author muzhenhua
 * @create 2018-09-05 15:21
 **/
public class NoticeModel implements Serializable {

    /**
     * 发送应用id
     */
    private Integer appId;

    /**
     * 消息id,发送方生成，格式为appId+":"+uuid
     */
    private String msgId;

    /**
     * 通标识，如果设置了只会发送指定的通，不设置的话将会发送所有后台设置的通
     */
    private String toonType;

    /**
     * 接收用户id
     */
    private Integer userId;

    /**
     * 	推送信息内容推送信息内容
     */
    private NoticePushInfo noticePushInfo;
    //private Status status;
    /**
     *  消息内容格式
     */
    private NoticeInfo noticeInfo;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getToonType() {
        return toonType;
    }

    public void setToonType(String toonType) {
        this.toonType = toonType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public NoticePushInfo getNoticePushInfo() {
        return noticePushInfo;
    }

    public void setNoticePushInfo(NoticePushInfo noticePushInfo) {
        this.noticePushInfo = noticePushInfo;
    }

    public NoticeInfo getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(NoticeInfo noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    @Override
    public String toString() {
        return "NoticeModel{" +
                "appId=" + appId +
                ", msgId='" + msgId + '\'' +
                ", toonType='" + toonType + '\'' +
                ", userId=" + userId +
                ", noticePushInfo=" + noticePushInfo +
                ", noticeInfo=" + noticeInfo +
                '}';
    }
}
