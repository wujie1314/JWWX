package org.jiaowei.entity;

/**
 * Created by alex on 15-11-30.
 * 图片消息实体
 */
public class PictureMsgEntity extends MsgBaseEntity {

    private String PicUrl;
    private String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
