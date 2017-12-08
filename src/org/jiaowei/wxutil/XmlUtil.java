package org.jiaowei.wxutil;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jiaowei.util.FastJsonUtil;
import org.jiaowei.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15-11-27.
 */
public class XmlUtil {

    private static Logger logger = Logger.getLogger(XmlUtil.class);

    /**
     * 解析微信发来的请求（XML）
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        reader.setEncoding("UTF-8");
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        // 释放资源
        inputStream.close();
        return map;
    }

    /**
     * 生成回复微信服务器的XML
     *
     * @return
     */
    public static String beanToXML(Object obj, Class cls,final List<String> excludeNodes) {
        XStream xstream = new XStream(new XppDriver() {
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    // 对所有xml节点的转换都增加CDATA标记
                    boolean cdata = true;

                    @SuppressWarnings("unchecked")
                    public void startNode(String name, Class clazz) {
                        super.startNode(name, clazz);
                        cdata = true;
                        if (null != excludeNodes) {
                            for (String s : excludeNodes) {
                                if (s.equals(name)) {
                                    cdata = false;
                                    break;
                                }
                            }
                        }
                    }

                    protected void writeText(QuickWriter writer, String text) {
                        if (cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });
        xstream.alias("xml", cls);
        return xstream.toXML(obj);
    }


    /**
     * 生成文本消息的回复
     * @param map
     * @return
     */
    public static String genTextResponseMsg(Map<String, String> map) {
        if (5 > map.size()) {
            logger.info("收到的微信发来的数据异常：" + FastJsonUtil.toJson(map));
            return "";
        }
        String temp = String.format("<xml>\n" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
                        "<CreateTime>%d</CreateTime>\n" +
                        "<MsgType><![CDATA[text]]></MsgType>\n" +
                        "<Content><![CDATA[%s]]></Content>\n" +
                        "</xml>", map.get("FromUserName"), map.get("ToUserName"),
                Integer.valueOf(map.get("CreateTime")), "您好，您发的内容是：" + map.get("Content"));
        return temp;
    }

    /**
     * 生成文本消息的回复
     * @param
     * @return
     */
    public static String genTextResponseMsg(String toUserName, String fromUserName,String content) {

        String temp = String.format("<xml>\n" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
                        "<CreateTime>%d</CreateTime>\n" +
                        "<MsgType><![CDATA[text]]></MsgType>\n" +
                        "<Content><![CDATA[%s]]></Content>\n" +
                        "</xml>", toUserName, fromUserName,System.currentTimeMillis()/1000, content);
        return temp;
    }

    /**
     * 生成文本消息的回复
     * @param map
     * @return
     */
    public static String genTextResponseMsg(Map<String, String> map,String content) {
        if (5 > map.size()) {
            logger.info("收到的微信发来的数据异常：" + FastJsonUtil.toJson(map));
            return "";
        }
        String temp = String.format("<xml>\n" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
                        "<CreateTime>%d</CreateTime>\n" +
                        "<MsgType><![CDATA[text]]></MsgType>\n" +
                        "<Content><![CDATA[%s]]></Content>\n" +
                        "</xml>", map.get("FromUserName"), map.get("ToUserName"),
                Integer.valueOf(map.get("CreateTime")), content);
        return temp;
    }


    /**
     * 生成图片回复消息
     *
     * @param map
     * @return
     */
    public static String genImgResponseMsg(Map<String, String> map) {
        if (5 > map.size()) {
            logger.info("收到的微信发来的数据异常：" + FastJsonUtil.toJson(map));
            return "";
        }
        String temp = String.format("<xml>\n" +
                "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
                "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
                "<CreateTime>%d</CreateTime>\n" +
                "<MsgType><![CDATA[image]]></MsgType>\n" +
                "<Image>\n" +
                "<MediaId><![CDATA[%s]]></MediaId>\n" +
                "</Image>\n" +
                "</xml>", map.get("FromUserName"), map.get("ToUserName"),
                Integer.valueOf(map.get("CreateTime")),map.get("MediaId"));
        return temp;
    }


    /**
     * 生成回复的语音消息
     * @param map
     * @return
     */
    public static String genVoiceResponseMsg(Map<String, String> map) {
        if (5 > map.size()) {
            logger.info("收到的微信发来的数据异常：" + FastJsonUtil.toJson(map));
            return "";
        }
        String temp = String.format("<xml>\n" +
                "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
                "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
                "<CreateTime>%d</CreateTime>\n" +
                "<MsgType><![CDATA[voice]]></MsgType>\n" +
                "<Voice>\n" +
                "<MediaId><![CDATA[%s]]></MediaId>\n" +
                "</Voice>\n" +
                "</xml>", map.get("FromUserName"), map.get("ToUserName"),
                Integer.valueOf(map.get("CreateTime")),map.get("MediaId"));
        return temp;
    }

    /**
     * 生成视频消息
     * @param map
     * @return
     */
    public static String genVideoResponseMsg(Map<String, String> map) {
        if (5 > map.size()) {
            logger.info("收到的微信发来的数据异常：" + FastJsonUtil.toJson(map));
            return "";
        }
        String temp = String.format("<xml>\n" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
                        "<CreateTime>%d</CreateTime>\n" +
                        "<MsgType><![CDATA[video]]></MsgType>\n" +
                        "<Video>\n" +
                        "<MediaId><![CDATA[%s]]></MediaId>\n" +
                        "<Title><![CDATA[%s]]></Title>\n" +
                        "<Description><![CDATA[%s]]></Description>\n" +
                        "</Video> \n" +
                        "</xml>", map.get("FromUserName"), map.get("ToUserName"),
                Integer.valueOf(map.get("CreateTime")),map.get("MediaId"),"标题","这是描述");
        return temp;
    }

    /**
     * 生成图文消息
     * @param map
     * @return
     */
    public static String gen1ArticlesResponseMsg(Map<String, String> map,String title,String desc,String url) {
        if (5 > map.size()) {
            logger.info("收到的微信发来的数据异常：" + FastJsonUtil.toJson(map));
            return "";
        }
        String temp = String.format("<xml>\n" +
                "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
                "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
                "<CreateTime>%s</CreateTime>\n" +
                "<MsgType><![CDATA[news]]></MsgType>\n" +
                "<ArticleCount>1</ArticleCount>\n" +
                "<Articles>\n" +
                "<item>\n" +
                "<Title><![CDATA[%s]]></Title> \n" +
                "<Description><![CDATA[%s]]></Description>\n" +
//                "<PicUrl><![CDATA[picurl]]></PicUrl>\n" +
                "<Url><![CDATA[%s]]></Url>\n" +
                "</item>\n" +
                "</Articles>\n" +
                "</xml>",map.get("FromUserName"), map.get("ToUserName"),
                Integer.valueOf(map.get("CreateTime")),title,desc,url);
        return temp;
    }

    /**
     *将map中key的首字线转换为小写，方便将map转化为bean
     * @param map
     * @return
     */
    public static  Map<String,String> changeKeyFirstToLower(Map<String,String> map){
        Map<String,String> tmp = new HashMap<String,String>();
        if(null == map){
            logger.info(String.format("参数：%s->值：%s","map",map));
            return  tmp;
        }
        Iterator<String> it = map.keySet().iterator();
        String key = null;
        String value = null;
        while(it.hasNext()){
            key = it.next();
            value = map.get(key);
            tmp.put(StringUtil.firstLowerCase(key),value);
        }
        return tmp;
    }

}
