package org.jiaowei.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "WX_EXPRESSION_T", schema = "JWWX", catalog = "")
public class WxExpressionEntity {

	@Id
    @Column(name = "ID")
	private Integer id;//座席ID
	
	@Basic
	@Column(name = "SHOW_NAME")
	private String showName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	
}
