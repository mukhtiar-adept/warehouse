package com.progressoft.warehouse.aspect;


import com.progressoft.warehouse.util.WarehouseHealper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * The AOP component to perform logging.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@Component
@Aspect
public class AspectLogger {

    /**
     * The logger class name.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectLogger.class);

    /**
     * Log method entrance.
     *
     * @param joinPoint the joint point
     */
    @Before("execution(* *(..)) && @annotation(com.progressoft.warehouse.annotations.LogMethod)")
    public void logMethodAccessBefore(JoinPoint joinPoint) {
        String[] parameterNames = ((MethodSignature) (joinPoint.getSignature())).getParameterNames();
        if (parameterNames == null) {
            parameterNames = new String[joinPoint.getArgs().length];
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                parameterNames[i] = "arg" + i;
            }
        }
        WarehouseHealper.logEntrance(LOGGER, joinPoint.getSignature().toString(),
            parameterNames, joinPoint.getArgs());
    }

    /**
     * Log method exit.
     *
     * @param joinPoint the join point
     * @param result    the result
     */
    @AfterReturning(
        pointcut = "execution(* *(..)) && @annotation(com.progressoft.warehouse.annotations.LogMethod)",
        returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        WarehouseHealper.logExit(LOGGER, joinPoint.getSignature().toString(), result);
    }

    /**
     * Log exception.
     *
     * @param joinPoint the joint point
     * @param ex        the exception
     */
    @AfterThrowing(
        pointcut = "execution(* *(..)) && @annotation(com.progressoft.warehouse.annotations.LogMethod)",
        throwing = "ex")
    public void doRecoveryActions(JoinPoint joinPoint, Exception ex) {
        WarehouseHealper.logException(LOGGER, joinPoint.getSignature().toString(), ex);
    }
}
