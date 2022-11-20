package com.javapai.framework.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring容器工具类。<br>
 * 
 * <br>
 * <strong>使用场景：</strong> <br>
 * 1:如果要在静态方法中注入某个spring容器的bean时.<br>
 * 2:当某些没有纳入spring框架管理的类却要调用spring容器中的bean时。 <br>
 * <br>
 * <strong>引入方式：</strong><br>
 * {@link &lt;bean id="SpringContextHolder" class="com.javapai.framework.utils.UtilSpringContext" /&gt;}
 * <br>
 * <strong>OR</strong> <br>
 * {@link @ComponentScan com.javapai.framework.utils.UtilSpringContext} <br>
 * <br>
 * <strong>外部依赖：</strong> <br>
 * spring-context.jar包
 * 
 * @author pooja
 *
 */
//@Component
public class UtilSpringContext implements ApplicationContextAware {
	private static ApplicationContext applicationContext;// WebApplicationContextUtils

	/**
	 * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		if(null == UtilSpringContext.applicationContext) {
			UtilSpringContext.applicationContext = applicationContext;
		}
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		return (T) applicationContext.getBeansOfType(clazz);
	}

	/**
	 * 清除applicationContext静态变量.
	 */
	public static void cleanApplicationContext() {
		applicationContext = null;
	}

	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请参考类说明的“引用方式”章节进行工具类的bean定义引用。");
		}
	}
}
