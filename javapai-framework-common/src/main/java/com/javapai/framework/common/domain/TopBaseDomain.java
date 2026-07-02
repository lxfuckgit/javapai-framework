package com.javapai.framework.common.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * AbstractDomain:抽象实体类，可作为所有领域实体的基类。<br>
 * <P>
 * 抽象实体类是一个基础实体信息类，提供所有实体信息的ID、创建属性(createTime)、更新属性/锁属性(updateTime). <br>
 * <P>
 * 
 * @author liu.xiang
 *
 */
// @Entity
// @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
// public abstract class AbstractEntity implements Domain {
// }
@MappedSuperclass
public abstract class TopBaseDomain {
	//insertable 和 updatable 是JPA中 @Column 注解的两个属性，用于控制 Hibernate 在生成 SQL 语句时是否包含该字段。
	// 简单理解：
	// insertable = false：插入（INSERT）时忽略这个字段
	// updatable = false：更新（UPDATE）时忽略这个字段
	/**
	 * 创建时间(系统|数据库自动生成).<br>
	 * 格式:yyyy-MM-dd hh:mm;类型(VARCHAR2(16)):<br>
	 */
	@Column(name = "create_time", length = 30, nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp createTime; // private String createTime;
	/**
	 * 修改时间(数据版本锁).<br>
	 */
	@Column(name = "update_time", length = 30, updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Timestamp updateTime; // private String updateTime;

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
