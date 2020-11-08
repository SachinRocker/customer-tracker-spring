package com.customertracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	
	//set up pointcut declaration
	
	@Pointcut("execution(* com.customertracker.controller.*.*(..))")
	public void forControllerPackage() {}
	
	@Pointcut("execution(* com.customertracker.services.*.*(..))")
	public void forServicePackage() {}
	
	@Pointcut("execution(* com.customertracker.dao.*.*(..))")
	public void forDaoPackage() {}
	
	//combining pointcut expressions
	@Pointcut(" forControllerPackage() || forServicePackage() || forDaoPackage() ")
	public void forAppFlow() {}
	
	
	//add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		
		String method = joinPoint.getSignature().toShortString();
		System.out.println("method::"+method);
		Object[] args =  joinPoint.getArgs();
		for (Object param : args) {
			System.out.println("param::"+param);
			
		}
		
		
	}
	
	@AfterReturning(pointcut = "forAppFlow()",
			returning = "result")
	public void afterReturn(JoinPoint joinPoint, Object result) {

		String method = joinPoint.getSignature().toShortString();
		System.out.println("@AfterReturning ::"+method);
		
		System.out.println("result>>>"+result);
	}
	
	//add @AfterReturning advice

}
