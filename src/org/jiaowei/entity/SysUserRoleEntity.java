package org.jiaowei.entity;

import javax.persistence.*;

/**
 * Created by alex on 15-12-21.
 */
@Entity
@Table(name = "SYS_USER_ROLE_T", schema = "JWWX", catalog = "")
public class SysUserRoleEntity {
    private Integer id;
    private Integer userId;
    private Integer deptId;
    
    @Id
    @SequenceGenerator(name="SYS_USER_ROLE_S",sequenceName="SYS_USER_ROLE_S",allocationSize = 1)
    @GeneratedValue(generator="SYS_USER_ROLE_S",strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_ID")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

    @Basic
    @Column(name = "DEPT_ID")
	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
    
}
