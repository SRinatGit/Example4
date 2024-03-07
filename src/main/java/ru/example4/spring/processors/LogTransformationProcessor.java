package ru.example4.spring.processors;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class LogTransformationProcessor {
    private static final Logger logger = Logger.getLogger(LogTransformationProcessor.class);

    @Around("@annotation(ru.example4.spring.annotation.LogTransformation)")
    public Object logMethodCall(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Date dateCurrent = new Date();

        Object[] args = proceedingJoinPoint.getArgs();

        String methodName = proceedingJoinPoint.getSignature().getName();

        logger.info("<< date : " + dateCurrent.toString() + " methods " + methodName + "() - " + Arrays.toString(args));

        Object result = proceedingJoinPoint.proceed();

        logger.info(">> methods " + methodName + "() - " + result);

        return result;
    }
}
