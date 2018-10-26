package org.mqttproject;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
/**
 * https://dzone.com/articles/autowiring-spring-beans-into-classes-not-managed-by-spring
 */
public class BeanUtil implements ApplicationContextAware
{
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		context = applicationContext;
	}

	public static <T> T getBean(Class<T> beanClass)
	{
		return context.getBean(beanClass);
	}
}