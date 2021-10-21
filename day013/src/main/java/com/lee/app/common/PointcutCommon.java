package com.lee.app.common;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutCommon {
	
	@Pointcut("execution(* model.*.*Impl.get*(..))")
	public void aPointcut() {}
	@Pointcut("execution(* model.*.*Impl.*(..))")
	public void bPointcut() {}
}
