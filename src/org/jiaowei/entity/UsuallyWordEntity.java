package org.jiaowei.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by alex on 15-12-21.
 */
@Entity
@Table(name = "USUALLY_WORD_T", schema = "JWWX", catalog = "")
public class UsuallyWordEntity {
    private Integer id;
    private String title;
    private String usuallyWords;
    private Timestamp createTime;
    private String creator;
    private Integer isSystem;//1：系统常用语，不记时
    private Integer type;
    @Id
    @SequenceGenerator(name="USUALLY_WORD_S",sequenceName="USUALLY_WORD_S",allocationSize = 1)
    @GeneratedValue(generator="USUALLY_WORD_S",strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	@Basic
    @Column(name = "TITLE")
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Basic
    @Column(name = "USUALLY_WORDS")
    public String getUsuallyWords() {
        return usuallyWords;
    }

    public void setUsuallyWords(String usuallyWords) {
        this.usuallyWords = usuallyWords;
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
    @Column(name = "CREATOR")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    @Basic
    @Column(name = "IS_SYSTEM")
	public Integer getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
	}
	
	@Basic
    @Column(name = "TYPE")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
