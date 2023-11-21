package edu.storeservice.core.ch4;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
public class ExampleAspect {

    //@Around("execution(* edu.storeservice.core.ch2..*(..))")
    public Object AopTimer(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Thread.sleep(1000);
        Object result = joinPoint.proceed();

        stopWatch.stop();
        log.info("##### {} 성능 측정 ######", joinPoint.getSignature());
        log.info(stopWatch.prettyPrint());

        return result;

    }

}
