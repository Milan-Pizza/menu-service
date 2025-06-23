package app.cloudkitchen.menuservice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut for controller, service, and repository packages
    @Pointcut("execution(* app.cloudkitchen..controller..*(..)) || " +
              "execution(* app.cloudkitchen..service..*(..)) || " +
              "execution(* app.cloudkitchen..repository..*(..))")
    public void applicationPackages() {}

    @Before("applicationPackages()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "applicationPackages()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with result: {}",
                joinPoint.getSignature().toShortString(),
                result);
    }

    @AfterThrowing(pointcut = "applicationPackages()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in method: {} with cause: {}",
                joinPoint.getSignature().toShortString(),
                exception.getMessage());
    }
}