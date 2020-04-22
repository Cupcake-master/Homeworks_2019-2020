package ru.itis.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionAspect {

    @AfterThrowing(pointcut = "execution(* ru.itis.controller.MainController.raw())", throwing = "ex", argNames = "ex")
    public void logAfterThrowingAllMethods(Exception ex){
        System.err.println("LoggingAspect.logAfterThrowingAllMethods() " + ex);
    }

    @AfterThrowing(pointcut = "execution(* ru.itis.controller.MainController.raw())", throwing = "ex")
    public void afterUsersTry(Exception ex) {
        System.out.println(4);
    }

    @AfterThrowing(pointcut = "execution(* ru.itis.controller.MainController.raw()))", throwing = "ex")
    public void afterUserTry(Exception ex, JoinPoint joinPoint)throws EmptyResultDataAccessException {
        System.out.println(3);
    }

    @AfterThrowing(pointcut = "execution(* ru.itis.controller.MainController.raw())", throwing = "ex")
    public void afterDeleteTry(JoinPoint joinPoint,Exception ex) throws EmptyResultDataAccessException {
        System.out.println(2);
    }

    @AfterThrowing(pointcut = "execution(* ru.itis.controller.MainController.raw()))", throwing = "ex")
    public void registrationFailed(JoinPoint joinPoint,Exception ex) {
        System.out.println(1);
    }
}
