package com.javapai.framework.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring容器工具类。<br>
 * 
 * <br>
 * <strong>使用场景：</strong> <br>
 * 1:如果要在静态方法中注入某个spring容器的bean时.<br>
 * 2:当某些没有纳入spring框架管理的类却要调用spring容器中的bean时。 <br>
 * <br>
 * <strong>引入过程：</strong><br>
 * 1、注入UtilSpringContext实例。 在主类上注入实例，例如：<br>
 * {@linkplain @Import(UtilSpringContext.class) } <br>
 * <br>
 * <strong>OR</strong> <br>
 * <br>
 * {@code @Bean} <br>
 * public UtilSpringContext getUtilSpringContext() {<br>
 * return new UtilSpringContext();<br>
 * }<br>
 * <br>
 * 
 * 2、使用UtilSpringContext对象。 <br>
 * UtilSpringContext.getBean("beanName");<br>
 * <br>
 * <strong>外部依赖：</strong> <br>
 * spring-context.jar包
 * 
 * @author pooja
 *
 */
public class UtilSpringContext implements ApplicationContextAware {
	/**
	 * 程序上下文管理器。
	 */
	private static ApplicationContext applicationContext;// WebApplicationContextUtils

	/**
	 * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (null == UtilSpringContext.applicationContext) {
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
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		return getApplicationContext().getBean(clazz);
	}
	
	/**
	 * 通过name,以及Clazz返回指定的Bean
	 * 
	 * @param <T>
	 * @param name
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
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
