package net.bounceme.chronos.springboot.backend.apirest.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.springboot.backend.apirest.utils.LogWrapper;

@Configuration
@Aspect
@Slf4j
public class TimeTraceAspect {

	@Around("net.bounceme.chronos.springboot.backend.apirest.aspects.CommonJoinPointConfig.trackTimeAnnotation()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		Object returnProceed = joinPoint.proceed();

		long timeTaken = System.currentTimeMillis() - startTime;
		LogWrapper.debug(log, "%s execution time: %d milliseconds", joinPoint.getSignature(), timeTaken);

		return returnProceed;
	}
}
