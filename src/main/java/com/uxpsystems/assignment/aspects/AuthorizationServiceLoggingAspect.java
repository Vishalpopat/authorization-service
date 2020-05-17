package com.uxpsystems.assignment.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@Log4j2
public class AuthorizationServiceLoggingAspect {
	
	 @Around("execution(public *  com.uxpsystems.assignment.service..*.*(..))"
				+ "|| execution(public *  com.uxpsystems.assignment.controller..*.*(..) )"
				+ "|| execution(public * com.uxpsystems.assignment.repository..*.*(..))"
				+ "|| execution(public * com.uxpsystems.assignment.streams..*.*(..)))"
				+ "|| execution(public * com.uxpsystems.assignment.util..*.*(..)))")
		  public Object aroundMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
		    String qualifiedMethodName = joinPoint.getSignature().toShortString() ;

		    long startTime = System.currentTimeMillis();
		    log.info("Entering method {} with argument(s): {}", qualifiedMethodName, joinPoint.getArgs());
		    Object output = joinPoint.proceed();
		    long elapsedTime = System.currentTimeMillis() - startTime;
		    log.info("Exiting method {} . Execution Time: {} ms", qualifiedMethodName, elapsedTime);
		    return output;
		  }


		  @AfterThrowing(
		      pointcut = ("execution( *  com.uxpsystems.assignment.service..*.*(..) ) "
		              + "|| execution(*  com.uxpsystems.assignment.controller..*.*(..) )"
		              + "|| execution(public * com.uxpsystems.assignment.repository..*.*(..))"
		              + "|| execution(public * com.uxpsystems.assignment.util..*.*(..)))"),
		      throwing = "exception")
		  public void afterThrowing(final JoinPoint joinPoint, Exception exception) {
			  log.error("Exception thrown.",  exception);
		  }


	
}
