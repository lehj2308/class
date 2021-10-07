package com.lee.app.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class AroundAdvice {
	public Object pringLog(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("pjp인자를 갖는 메서드에서 출력하는 문구 -1");
		Object obj=pjp.proceed();
		System.out.println("pjp인자를 갖는 메서드에서 출력하는 문구 -2");
		return obj;
	}
}
