package com.myprojects.admin.aspect;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceLoggerAspect {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Around("@annotation(CheckMethod)")
	public Object logPerformance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		logger.info(String.format("%s is started.", proceedingJoinPoint.getSignature()));
		try {
			return proceedingJoinPoint.proceed();
		} catch (Exception e) {
			logger.error("Error Report :: " + e.getMessage(), e);
			return proceedingJoinPoint.proceed();

		} finally {
			logger.info(String.format("%s is ended.", proceedingJoinPoint.getSignature()));
		}
	}

}
