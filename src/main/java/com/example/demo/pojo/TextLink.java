package com.example.demo.pojo;

/**
 * @PROJECT_NAME: hs-access-hospital-senddata-standard
 * @PACKAGE_NAME: com.hundsun.med.hdp.service.pt.pojo
 * @CLASS_NAME: TextLink
 * @CREATE_USER: 谢晗
 * @DATE: 2019/8/30
 * @TIME: 15:27
 * @Description:
 **/
public class TextLink {

    private String text;
    private String color;
    private String href;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
