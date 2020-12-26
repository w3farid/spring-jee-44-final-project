package com.todo.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LoggingHandler {
	private Logger LOGGER = LoggerFactory.getLogger(LoggingHandler.class);

	@Pointcut("within(com.todo.controller..*)")
	public void logForCommon() {
	}

	

	@Around("logForCommon()")
	public Object logAround(ProceedingJoinPoint joinPoint) {
		long startTime = System.currentTimeMillis();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		// Get intercepted method details
		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();

		final StopWatch stopWatch = new StopWatch();

		// Measure method execution time
		stopWatch.start();
		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		stopWatch.stop();
		// Log method execution time
		LOGGER.info(
				"Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");

		return result;
	}

	
}
