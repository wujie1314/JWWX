package org.jiaowei.entity;

import javax.persistence.*;
/**
 * Created by alex on 15-12-21.
 */
@Entity
@Table(name = "USUALLY_WORD_TYPE", schema = "JWWX", catalog = "")
public class UsuallyWordTypeEntity {
    private Integer id;
    private Integer deptId;
    private String name;
    
    @Id
    @SequenceGenerator(name="USUALLY_WORD_TYPE_S",sequenceName="USUALLY_WORD_TYPE_S",allocationSize = 1)
    @GeneratedValue(generator="USUALLY_WORD_TYPE_S",strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	@Basic
    @Column(name = "DEPTID")
	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Basic
    @Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
