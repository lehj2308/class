package com.lee.app.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdvice {
	@Before("PointcutCommon.aPointcut()")
	public void printLog(JoinPoint jp) {
		Object[] args=jp.getArgs();
		String name=jp.getSignature().getName();
		System.out.println("========");
		System.out.println("데이터 출력이 시작됩니다!");
		System.out.println("메서드명 : "+name);
		System.out.println("매개변수정보 : "+args[0]);
		System.out.println("========");
	}
}
