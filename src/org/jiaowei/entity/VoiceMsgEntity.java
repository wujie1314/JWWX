package org.jiaowei.entity;

/**
 * Created by alex on 15-11-30.
 * 语音消息
 */
public class VoiceMsgEntity extends MsgBaseEntity {

    private String MediaId;
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
