package com.lee.app.common;

import org.aspectj.lang.JoinPoint;

import model.tMember.TMemberVO;

public class AfterReturningAdvice {

	public void printLog(JoinPoint jp, Object obj) {
		String name = jp.getSignature().getName();
		System.out.println("메서드명 : " + name);
		if (obj instanceof TMemberVO) {
			TMemberVO data = (TMemberVO) obj;
			if (data.getRole().equals("ADMIN")) {
				System.out.println("관리자 로그인");
			} else {
				System.out.println("일반사용자 로그인");
			}
		}

		System.out.println("get()와 조합되는 returning");
	}
}
