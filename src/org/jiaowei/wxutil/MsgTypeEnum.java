package org.jiaowei.wxutil;

/**
 * Created by alex on 15-11-30.
 * 微信消息类型的枚举
 */
public  enum MsgTypeEnum {
    EVENT(0,"event"),TEXT(1,"text"),IMG(2,"image"),VOICE(3,"voice"),VIDEO(4,"video"),
    SHORTVIDEO(5,"shortvideo"),LOCATION(6,"location"),LINK(7,"link");
    private Integer key;
    private String value;
    MsgTypeEnum(int key, String value){
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
