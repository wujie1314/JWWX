package org.jiaowei.entity;

/**
 * Created by alex on 15-11-30.
 * 文本消息实体
 */
public class TextMsgEntity extends MsgBaseEntity {
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
