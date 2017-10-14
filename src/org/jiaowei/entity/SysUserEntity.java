package org.jiaowei.entity;

import org.jiaowei.service.SysUserService;
import org.jiaowei.util.ApplicationContextUtil;
import org.jiaowei.util.StringUtil;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "SYS_USER_T", schema = "JWWX", catalog = "")
public class SysUserEntity {
    private Integer id;
    private String userId;
    private String roleId;
    private String roleName;
    private String userName;
    private Integer deptId;
    private String deptName;
    private Integer customerNum;//座席最大接待用户数
    private Integer changeNum;//系统默认
    private int isBusy;
    private int isAdmin;
    private String status;
    private Long heatTimes = 0l;
    private String deptIdHn; 
    private Integer serviceCustomerNum;//座席服务数
    private Double serviceFreeNum = 1.0d;//空闲服务数比例
    
    
	@Transient
    public Integer getServiceCustomerNum() {
		return serviceCustomerNum;
	}

	public void setServiceCustomerNum(Integer serviceCustomerNum) {
		this.serviceCustomerNum = serviceCustomerNum;
	}

	@Transient
	public Double getServiceFreeNum() {
		return serviceFreeNum;
	}

	public void setServiceFreeNum(Double serviceFreeNum) {
		this.serviceFreeNum = serviceFreeNum;
	}


	@Transient
    public Long getHeatTimes() {
		return heatTimes;
	}

	public void setHeatTimes(Long heatTimes) {
		this.heatTimes = heatTimes;
	}

	@Id
    @SequenceGenerator(name="SYS_USER_S",sequenceName="SYS_USER_S",allocationSize = 1)
    @GeneratedValue(generator="SYS_USER_S",strategy=GenerationType.SEQUENCE)
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
        if(!StringUtil.isEmpty(userId)){
            SysUserService sysUserService = (SysUserService) ApplicationContextUtil.getBean("sysUserService");
            String sql  = "select t1.ID,t1.ROLE_NAME from ROLE_T t1,USER_ROLE_T t2 where t2.ROLE_ID = t1.ROLE_ID AND t2.USER_ID ='"+userId+"'";
            List<Object> list =sysUserService.findBySQL(sql);
            if(list.size()>0) {
                Object[] obj = (Object[]) list.get(0);
                if(obj.length>1) {
                    setRoleId(obj[0].toString());
                    setRoleName(obj[1].toString());
                }
            }
        }
    }

    @Basic
    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "DEPT_ID")
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
        if(deptId!=null){
            SysUserService sysUserService = (SysUserService) ApplicationContextUtil.getBean("sysUserService");
            String sql  = "select DWQC from DEPART_T where ID ="+deptId;
            List<Object> list =sysUserService.findBySQL(sql);
            if(list.size()>0) {
                Object obj = (Object) list.get(0);
                setDeptName(obj.toString());
            }
        }
    }

    @Basic
    @Column(name = "CUSTOMER_NUM")
    public Integer getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(Integer customerNum) {
        this.customerNum = customerNum;
    }

    @Basic
    @Column(name = "CHANGE_NUM")
    public Integer getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(Integer changeNum) {
        this.changeNum = changeNum;
    }
    
    @Basic
    @Column(name = "DEPT_ID_HN")
    public String getDeptIdHn() {
		return deptIdHn;
	}

	public void setDeptIdHn(String deptIdHn) {
		this.deptIdHn = deptIdHn;
	}

	@Transient
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Transient
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Transient
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    @Transient
	public int getIsBusy() {
		return isBusy;
	}

	public void setIsBusy(int isBusy) {
		this.isBusy = isBusy;
	}
    private int readed;

    @Transient
	public int getReaded() {
		return readed;
	}

	public void setReaded(int readed) {
		this.readed = readed;
	}

	@Transient
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Transient
	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
    
}
