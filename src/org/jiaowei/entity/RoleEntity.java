package org.jiaowei.entity;

import javax.persistence.*;

/**
 * Created by alex on 15-12-21.
 */
@Entity
@Table(name = "ROLE_T", schema = "JWWX", catalog = "")
public class RoleEntity {
    private Integer id;
    private String roleId;
    private String roleName;

    @Id
    @SequenceGenerator(name="ROLE_S",sequenceName="ROLE_S",allocationSize = 1)
    @GeneratedValue(generator="ROLE_S",strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ROLE_ID")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "ROLE_NAME")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
