package com.lee.app.common;

import org.aspectj.lang.JoinPoint;

public class AfterAdvice {
	public void printLog(JoinPoint jp) {
		Object[] args=jp.getArgs();
		String name=jp.getSignature().getName();
		
		System.out.println("메서드명 : "+name);
		System.out.println("매개변수정보 : "+args[0]);
		System.out.println("핵심관심 후에 호출됨");
	}
}
