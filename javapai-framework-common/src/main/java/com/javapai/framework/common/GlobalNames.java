package com.javapai.framework.common;

/**
 * 系统全局常量(System Constants)
 * 
 * @author Lew_Xiang
 * @version 1.0
 * @since 2011-5-3
 * 
 */
public class GlobalNames {
	/****************************************************************/
	/**
	 * ACL.主体类型：用户
	 */
	public static final String TYPE_USER = "user";

	/**
	 * ACL.主体类型：角色
	 */
	public static final String TYPE_ROLE = "role";

	/**
	 * ACL.授权状态:permission中的操作类型(create)
	 */
	public static final int CREATE = 0;

	/**
	 * ACL.授权状态:permission中的操作类型(read)
	 */
	public static final int READ = 1;

	/**
	 * ACL.授权状态:permission中的操作类型(update)
	 */
	public static final int UPDATE = 2;

	/**
	 * ACL.授权状态:permission中的操作类型(delete)
	 */
	public static final int DELETE = 3;

	/**
	 * ACL.继承状态:继承
	 */
	public static final int ACL_YES = 1;

	/**
	 * ACL.继承状态:不继承
	 */
	public static final int ACL_NO = 0;

	/**
	 * ACL.继承状态:不确定
	 */
	public static final int ACL_NEUTRAL = -1;

	/****************************************************************/
	/**
	 * 版权、版本信息
	 */
	public static final String COPY_RIGHT = "XXXXXX小组";
	/**
	 * 国际通用NULL值的代替字符(替代后可提高索引查询)
	 */
	public static final String NULL_STRING ="_NA_";
	
	/****************************************************************/
	/**
	 * 系统临时目录路径
	 */
	public static final String TEM_DIR = System.getProperty("java.io.tmpdir");
	/**
	 * 邮件配置文件路径
	 */
	public static final String EMAIL_CONFIG = "config/email.properties";
	/**
	 * 数据库配置文件路径
	 */
	public static final String DB_CONFIG = "config/db_config.propertie";
	/**
	 * log4j配置文件路径
	 */
	public static final String LOG_CONFIG = "config/log4j.propertie";


}