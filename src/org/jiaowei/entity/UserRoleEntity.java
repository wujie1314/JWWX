package org.jiaowei.entity;

import javax.persistence.*;

/**
 * Created by alex on 15-12-21.
 */
@Entity
@Table(name = "USER_ROLE_T", schema = "JWWX", catalog = "")
public class UserRoleEntity {
    private Integer id;
    private String userId;
    private String roleId;

    @Id
    @SequenceGenerator(name="USER_ROLE_S",sequenceName="USER_ROLE_S",allocationSize = 1)
    @GeneratedValue(generator="USER_ROLE_S",strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "ROLE_ID")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
