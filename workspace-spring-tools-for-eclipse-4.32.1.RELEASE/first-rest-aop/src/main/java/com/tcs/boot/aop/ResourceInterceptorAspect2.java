//package com.tcs.boot.aop;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import com.tcs.boot.entity.Loan;
//
//import lombok.extern.slf4j.Slf4j;
//
//// Test only get method returns 1 object 
//@Aspect
//@Component
//@Slf4j
//public class ResourceInterceptorAspect2 {
//
//	@AfterReturning(value = "execution( * com.tcs.boot.controller .*.*(..))", returning = "product")
//
//	public void afterRet(JoinPoint joinPoint, Loan loan) {
//
//		log.info("After Returning method invoked :: " + loan);
//
//	}
//
//	@AfterThrowing(value = "execution( * com.tcs.boot.controller .*.*(..))", throwing = "e")
//	public void afterThrow(JoinPoint joinPoint, Exception e) {
//
//		log.info("After method invoked :: " + e);
//
//	}
//
//}