package dao.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LoggingAspect {

    @Around("az.online.shop.aop.CommonPointcuts.isServiceLayer()")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodName = joinPoint.getSignature().getName();
        String clazz = joinPoint.getTarget().toString();
        Object[] args = joinPoint.getArgs();
        log.info("before - invoked method: {} in class: {} args: {}", methodName, clazz, args);
        try {
            Object result = joinPoint.proceed();
            log.info("after returning - invoked method: {} in class: {} args: {}", methodName, clazz, args);
            return result;
        } catch (Throwable ex) {
            log.error("after throwing - invoked method: {} in class: {} with exception message: {}", methodName, clazz, ex.getMessage());
            throw ex;
        }
    }
}