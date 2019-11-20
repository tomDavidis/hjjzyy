package com.yuntai.upp.access.util;

import com.yuntai.upp.client.basic.util.LoggerUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @description
 * @className XmlUtil
 * @package com.yuntai.upp.support.util
 * @author duanhr@hsyuntai.com
 * @date 2019/6/14 13:42
 * @copyright 版权归 HSYUNTAI 所有
 */
@Slf4j
public class XmlUtil {

    public static Map<String, Object> dom2Map(Document doc) {
        Map<String, Object> map = new HashMap<>(8);
        if (doc == null) {
            return map;
        }
        Element root = doc.getRootElement();
        for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
            Element e = (Element) iterator.next();
            List list = e.elements();
            if (list.size() > 0) {
                map.put(e.getName(), dom2Map(e));
            } else {
                map.put(e.getName(), e.getText());
            }
        }
        return map;
    }

    public static Map dom2Map(Element e) {
        Map map = new HashMap(8);
        List list = e.elements();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Element iter = (Element) list.get(i);
                List mapList = new ArrayList();
                if (iter.elements().size() > 0) {
                    Map m = dom2Map(iter);
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!"java.util.ArrayList".equals(obj.getClass().getName())) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if ("java.util.ArrayList".equals(obj.getClass().getName())) {
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    } else {
                        map.put(iter.getName(), m);
                    }
                } else {
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!"java.util.ArrayList".equals(obj.getClass().getName())) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if ("java.util.ArrayList".equals(obj.getClass().getName())) {
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    } else {
                        map.put(iter.getName(), iter.getText());
                    }
                }
            }
        } else {
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    /**
     * @description
     * @param xmlStr
     * @return java.util.Map
     * @author duanhr@hsyuntai.com
     * @date 2019/11/2 16:11
     */
    public static Map xml2map(String xmlStr) {
        try {
            Document doc = DocumentHelper.parseText(xmlStr);
            return dom2Map(doc);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @description 转成排序MAP
     * @className XmlUtil
     * @package com.yuntai.upp.support.util
     * @author duanhr@hsyuntai.com
     * @date 2019/11/2 16:11
     * @copyright 版权归 HSYUNTAI 所有
     */
    public static TreeMap xml2TreeMap(String xmlStr) {
        Map map = xml2map(xmlStr);
        return null == map ? null : new TreeMap<>(map);
    }

    /**
     * @description 输入流为xml, 解析成MAP
     * @param stream
     * @return java.util.TreeMap<java.lang.String       ,       java.lang.Object>
     * @author duanhr@hsyuntai.com
     * @date 2019/11/2 16:10
     */
    public static TreeMap<String, Object> toMap(@NonNull InputStream stream) throws Exception {
        TreeMap<String, Object> data = new TreeMap<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        org.w3c.dom.Document doc = documentBuilder.parse(stream);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int idx = 0; idx < nodeList.getLength(); ++idx) {
            Node node = nodeList.item(idx);
            if (node.getNodeType() == 1) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                data.put(element.getNodeName(), element.getTextContent());
            }
        }
        try {
            stream.close();
        } catch (Exception exception) {
            LoggerUtil.error(exception, LOGGER);
        }
        return data;
    }
}
