package com.example.demo.pojo;

/**
 * @PROJECT_NAME: hs-access-hospital-senddata-standard
 * @PACKAGE_NAME: com.hundsun.med.hdp.service.pt.pojo
 * @CLASS_NAME: Title
 * @CREATE_USER: 谢晗
 * @DATE: 2019/8/30
 * @TIME: 14:26
 * @Description:
 **/
public class Title {

    private String text;
    private String img;
    private String href;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
