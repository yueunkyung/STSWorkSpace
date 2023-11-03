package com.shinhan.day05;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// log를 남기는 어드바이스

//보조업무
@Component
@Aspect
public class LoggingAdvice {
	@Pointcut("execution( * add(..))")
	public void aa() {
		//메서드 이름은 상과넝ㅄ음, 메서드 형태를 만들고 @Pointcut 작성
	}

	@After("aa()")
	public void f2(JoinPoint jp) {
		System.out.println("@After===>>"+jp.getSignature().getName());
	}
	
	@Before("aa()")
	public void f1(JoinPoint jp) {
		System.out.println("before===>>"+jp.getSignature().getName());
	}
	
	@Around("aa()")
	public Object arroundMethod2(ProceedingJoinPoint jp) throws Throwable {
		//보조업무
		System.out.println("=============LoggingAdvice보조업무==============");
		System.out.println(jp.getSignature().getName()+"!!!!!!!!메서드 호출전!!!!!!!!");
		//주업무
		Object obj = jp.proceed();
		//보조업무
		System.out.println(jp.getSignature().getName()+"!!!!!!!!메서드 호출후!!!!!!!!");
		System.out.println("******************************");
		
		return obj;
	}
	
}
