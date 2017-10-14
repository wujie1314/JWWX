package org.jiaowei.util;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @author  
 *
 */
//一定要在spring.xml中加上:
//<bean id="springContextUtil " class="com.jundun.common.util.ApplicationContextUtil" scope="singleton"/>
//当对SpringContextUtil 实例时就自动设置applicationContext,以便后来可直接用applicationContext
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContxt;

	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		ApplicationContextUtil.applicationContxt = context;
	}

	public static ApplicationContext getContext() {
		assertContextInjected();
		return applicationContxt;
	}
	/**
	 * 获取对象
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		assertContextInjected();
		return ApplicationContextUtil.applicationContxt.getBean(name);
	}

	/**
	 * 获取类型为requiredType的对象
	 * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
	 * @param name       bean注册名
	 * @param requiredType 返回对象类型
	 * @return Object 返回requiredType类型对象
	 * @throws BeansException
	 */
	public static Object getBean(String name, Class requiredType) throws BeansException {
		assertContextInjected();
		return ApplicationContextUtil.applicationContxt.getBean(name, requiredType);
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		assertContextInjected();
		return ApplicationContextUtil.applicationContxt.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 * @param name
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		assertContextInjected();
		return ApplicationContextUtil.applicationContxt.isSingleton(name);
	}

	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException
	 */
	public static Class getType(String name) throws NoSuchBeanDefinitionException {
		assertContextInjected();
		return ApplicationContextUtil.applicationContxt.getType(name);
	}

	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		assertContextInjected();
		return ApplicationContextUtil.applicationContxt.getAliases(name);
	}

	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected() {
		Validate.validState(applicationContxt != null,
				"applicaitonContext属性未注入, " +
						"请在applicationContext.xml中定义ApplicationContextUtil.");
	}

}
