package com.example.demo.util;

//import lombok.Data;

/**
 * @author muzhenhua
 * @create 2018-09-05 15:26
 **/

public class NoticePushInfo {
    /**
     * 应用Id
     */
    private Integer appId;

    /**
     * 通知栏展示的通知的描述， 长度不超过128个汉字。
     */
    private String description;

    /**
     *  0 表示通知栏消息；1 表示透传消息 。默认为0
     */
    private String passThrough;

    /**
     *  消息的内容。（注意：需要对payload字符串做urlencode处理）， 长度不超过4096个字节
     */
    private String payload;

    /**
     * 推送标题
     */
    private String title;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassThrough() {
        return passThrough;
    }

    public void setPassThrough(String passThrough) {
        this.passThrough = passThrough;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "NoticePushInfo{" +
                "appId=" + appId +
                ", description='" + description + '\'' +
                ", passThrough='" + passThrough + '\'' +
                ", payload='" + payload + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
