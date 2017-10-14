package org.jiaowei.entity;

import javax.persistence.*;

/**
 * Created by alex on 15-12-21.
 */
@Entity
@Table(name = "DEPART_T", schema = "JWWX", catalog = "")
public class DepartEntity {
    private Integer id;
    private String dwqc;
    private String jgdm;

    @Id
    @SequenceGenerator(name="DEPART_S",sequenceName="DEPART_S",allocationSize = 1)
    @GeneratedValue(generator="DEPART_S",strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DWQC")
    public String getDwqc() {
        return dwqc;
    }

    public void setDwqc(String dwqc) {
        this.dwqc = dwqc;
    }

    @Basic
    @Column(name = "JGDM")
    public String getJgdm() {
        return jgdm;
    }

    public void setJgdm(String jgdm) {
        this.jgdm = jgdm;
    }
}
