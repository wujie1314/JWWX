package org.jiaowei.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "WX_SBLK_IMG_T", schema = "JWWX", catalog = "")
public class WxSblkImgEntity {
    private Integer id;
    private String imageSrc;
    private Integer sblkId;

    @Id
    @SequenceGenerator(name="WX_SBLK_IMG_S",sequenceName="WX_SBLK_IMG_S",allocationSize = 1)
    @GeneratedValue(generator="WX_SBLK_IMG_S",strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IMAGE_SRC")
	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

    @Basic
    @Column(name = "SBLK_ID")
	public Integer getSblkId() {
		return sblkId;
	}

	public void setSblkId(Integer sblkId) {
		this.sblkId = sblkId;
	}
    
}
