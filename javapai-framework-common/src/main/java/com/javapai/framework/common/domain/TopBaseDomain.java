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
	/**
	 * 创建时间(系统|数据库自动生成).<br>
	 * 格式:yyyy-MM-dd hh:mm;类型(VARCHAR2(16)):<br>
	 */
	@Column(name = "create_time", length = 30, nullable = false,insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp createTime; // private String createTime;//insertable = false是什么场景用
	/**
	 * 修改时间(数据版本锁).<br>
	 */
	@Column(name = "update_time", length = 30)
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
