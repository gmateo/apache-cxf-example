package org.anotes.services.translator.ws.aop;

import org.anotes.services.translator.ws.schema.ResultEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WsServicesAspect {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Pointcut("bean(*PTImpl)")
    private void serviceBean() {
    }

    @Pointcut("execution(public * *(..))")
    private void publicMethod() {
    }


    @Around("serviceBean() && publicMethod()")
    public Object processServicePtPublicMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().toShortString();
        try {
            LOG.info("Around: Before executing:" + method);
            Object obj = proceedingJoinPoint.proceed();
            LOG.info("Around: After executing:" + method);
            return obj;
        } catch (Throwable throwable) {
            LOG.error("Problems calling the method: " + method, throwable);
            String errorMsg = throwable.getMessage();
            Object responseObj = getResponseInstance(proceedingJoinPoint);
            BeanWrapper beanWrapper = new BeanWrapperImpl(responseObj);
            beanWrapper.setPropertyValue("resultCode", ResultEnum.ERROR);
            beanWrapper.setPropertyValue("errorMsg", errorMsg);
            return responseObj;
        }
    }

    private Object getResponseInstance(ProceedingJoinPoint proceedingJoinPoint) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String fullMethod = proceedingJoinPoint.getSignature().toLongString();
        String[] methodParts = fullMethod.split(" ");
        Class<?> aClass = getClass().getClassLoader().loadClass(methodParts[2]);
        return aClass.newInstance();
    }
}