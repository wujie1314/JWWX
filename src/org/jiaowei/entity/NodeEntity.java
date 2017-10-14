package org.jiaowei.entity;

import javax.persistence.*;

@Entity
@Table(name = "NODE_T", schema = "JWWX", catalog = "")
public class NodeEntity {
    private Integer id;
    private String name;
    private String code;
    private String trunk;
    private String no;
    private Double order;
    private String node;
    private String descript;
    private Integer trunkNo;
    
	@Id
    @SequenceGenerator(name="NODE_S",sequenceName="NODE_S",allocationSize = 1)
    @GeneratedValue(generator="NODE_S",strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        
    }

    @Basic
    @Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Basic
    @Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

    @Basic
    @Column(name = "TRUNK")
	public String getTrunk() {
		return trunk;
	}

	public void setTrunk(String trunk) {
		this.trunk = trunk;
	}

    @Basic
    @Column(name = "NO")
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

    @Basic
    @Column(name = "ORDER")
	public Double getOrder() {
		return order;
	}

	public void setOrder(Double order) {
		this.order = order;
	}

    @Basic
    @Column(name = "NODE")
	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

    @Basic
    @Column(name = "DESCRIPT")
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

    @Basic
    @Column(name = "TRUNK_NO")
	public Integer getTrunkNo() {
		return trunkNo;
	}

	public void setTrunkNo(Integer trunkNo) {
		this.trunkNo = trunkNo;
	}
	
}
