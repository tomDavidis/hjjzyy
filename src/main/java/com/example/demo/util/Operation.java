package com.example.demo.util;

import com.example.demo.pojo.ButtonLink;
import com.example.demo.pojo.ImgLink;
import com.example.demo.pojo.TextLink;

import java.util.List;

/**
 * @author muzhenhua
 * @create 2018-09-05 15:52
 **/
public class Operation {

    /**
     * 按钮链接数组
     */
    private List<ButtonLink> buttonLinks;

    /**
     * 图文连接数组
     */
    private List<ImgLink> imgLinks;

    /**
     *  文本连接数组
     */
    private List<TextLink> textLinks;

    public List<ButtonLink> getButtonLinks() {
        return buttonLinks;
    }

    public void setButtonLinks(List<ButtonLink> buttonLinks) {
        this.buttonLinks = buttonLinks;
    }

    public List<ImgLink> getImgLinks() {
        return imgLinks;
    }

    public void setImgLinks(List<ImgLink> imgLinks) {
        this.imgLinks = imgLinks;
    }

    public List<TextLink> getTextLinks() {
        return textLinks;
    }

    public void setTextLinks(List<TextLink> textLinks) {
        this.textLinks = textLinks;
    }
}
