package com.myli.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataAdvice {
    // 定义切点
    @Pointcut("execution(* com.myli.service.UserService.*(..))")
    public void servicePt() {}

    // 定义切面

    /**
     * 去除字符串两端的空格
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("servicePt()")
    public Object trimStr(ProceedingJoinPoint pjp) throws Throwable {
        //获得参数
        Object[] args = pjp.getArgs();
        //去除空格
        for (int i = 0; i < args.length; i++) {
            //判断参数是不是字符串
            //也可以用这个判断args[i].getClass().equals(String.class)
            if (args[i] instanceof String) {
                args[i] = ((String) args[i]).trim();
            }

        }
        Object ret = pjp.proceed(args);
        return ret;
    }
}
