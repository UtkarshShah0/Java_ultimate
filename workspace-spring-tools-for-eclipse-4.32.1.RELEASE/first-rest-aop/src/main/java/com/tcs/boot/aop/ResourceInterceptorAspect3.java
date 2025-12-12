//package com.tcs.boot.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.slf4j.Slf4j;
//
//import com.tcs.boot.entity.Loan;
//
//@Aspect
//@Component
//@Slf4j
//public class ResourceInterceptorAspect3 {
//
//	
//	  @Pointcut("execution( * com.tcs.boot.controller .*.*(..))")
//	  public void loggingPointCut() {}
//	  
//	  @Around("loggingPointCut()") public Object around(ProceedingJoinPoint
//	  joinPoint) throws Throwable {
//	  
//	  log.info("Before method invoked :: "+ joinPoint.getSignature());
//	  log.info("Before method invoked :: "+ joinPoint.getArgs()[0]);
//	  
//	  Object object = joinPoint.proceed();
//	  
//	  if(object instanceof Loan) log.info("After method invoked.... "+
//	  joinPoint.getSignature()); log.info("After method invoked :: "+
//	  joinPoint.getArgs()[0]); return object;
//	  
//	  }
//	 
//} 
//	 
