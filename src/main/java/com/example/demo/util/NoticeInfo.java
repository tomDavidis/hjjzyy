package com.example.demo.util;

import com.example.demo.pojo.Content;
import com.example.demo.pojo.Remark;
import com.example.demo.pojo.Status;
import com.example.demo.pojo.Title;

/**
 * @author muzhenhua
 * @create 2018-09-05 15:48
 **/
public class NoticeInfo {

    /**
     * 通知正文
     */
    private Content content;

    /**
     * 通知操作区
     */
    private Operation operation;

    /**
     * 	通知备注
     */
    private Remark remark;

    /**
     *  通知状态
     */
    private Status status;

    /**
     * 通知标题区
     */
    private Title title;


    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Remark getRemark() {
        return remark;
    }

    public void setRemark(Remark remark) {
        this.remark = remark;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "NoticeInfo{" +
                "content=" + content +
                ", operation=" + operation +
                ", remark=" + remark +
                ", status=" + status +
                ", title=" + title +
                '}';
    }
}
