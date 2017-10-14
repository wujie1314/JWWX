package org.jiaowei.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "MSG_FROM_CS_T", schema = "JWWX", catalog = "")
public class MsgFromCSEntity {
    private Integer id;
    private String fromUser;
    private String toUser;
    private String content;
    private Timestamp createTime;
    private Integer readed;

    @Id
    @SequenceGenerator(name="MSG_FROM_CS_S",sequenceName="MSG_FROM_CS_S",allocationSize = 1)
    @GeneratedValue(generator="MSG_FROM_CS_S",strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FROM_USER")
    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    @Basic
    @Column(name = "TO_USER")
    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Basic
    @Column(name = "CREATE_TIME")
    public Timestamp getCreateTime() {
        return createTime;
    }
	public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

	@Basic
    @Column(name = "READED")
	public Integer getReaded() {
		return readed;
	}

	public void setReaded(Integer readed) {
		this.readed = readed;
	}

}
