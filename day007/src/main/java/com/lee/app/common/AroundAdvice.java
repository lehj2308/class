package com.lee.app.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class AroundAdvice {
	public Object pringLog(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("pjp���ڸ� ���� �޼��忡�� ����ϴ� ���� -1");
		Object obj=pjp.proceed();
		System.out.println("pjp���ڸ� ���� �޼��忡�� ����ϴ� ���� -2");
		return obj;
	}
}
