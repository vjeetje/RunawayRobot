package xavier.rasschaert.hacker.org.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class PuzzleAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PuzzleAspect.class);

    @Around("execution(public * xavier.rasschaert.hacker.org.network.DataFetcher.*(..))")
    public Object logNetworkActivity(ProceedingJoinPoint pjp) throws Throwable {
        return executeAndLog(pjp);
    }

    @Around("execution(public * xavier.rasschaert.hacker.org.solver.*.*(..))")
    public Object logPuzzleSolving(ProceedingJoinPoint pjp) throws Throwable {
        return executeAndLog(pjp);
    }

    private Object executeAndLog(ProceedingJoinPoint pjp) throws Throwable {
        String signature = String.format("%s(%s)", pjp.getSignature().getName(), StringUtils.join(pjp.getArgs()));
        long startTime = System.currentTimeMillis();
        LOGGER.info(String.format("Start of method %s", signature));
        Object retVal = pjp.proceed();
        double time = (System.currentTimeMillis() - startTime) * 1.0 / 1000;
        LOGGER.info(String.format("%.3fs: method %s returned %s", time, signature, retVal));
        return retVal;
    }
}
