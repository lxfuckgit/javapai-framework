package com.javapai.framework.enums;

import java.util.HashMap;
import java.util.Map;

public enum GeoTypeEnums {
	EX_WAREHOUSE("EX_WAREHOUSE", "出库点数"), CONSUME("CONSUME", "支付点数"), COMPENSATE(
			"COMPENSATE", "补偿点数"), PUNISH("PUNISH", "处罚点数"), REWARD("REWARD",
			"奖励点数");
	final String id;
	final String desc;

	static final Map<String, GeoTypeEnums> map;

	static {
		map = new HashMap<String, GeoTypeEnums>();
		for (GeoTypeEnums status : values()) {
			map.put(status.id, status);
		}
	}

	private GeoTypeEnums(String id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static GeoTypeEnums getGeoTypeEnums(String id) {
		return map.get(id);
	}

	public String getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}
}
