package ru.itis.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

@Component
public class AspectOnAnnotation {

    @Around("@annotation(ru.itis.util.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        System.err.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
