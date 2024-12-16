package com.example.task3.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheAccessAspect {

    @Pointcut("@annotation(org.springframework.cache.annotation.Cacheable) && execution(* com.example.task3.services.DeviceService.getDeviceById(..))")
    public void cacheableMethods() {}

//    @Before("cacheableMethods() && @annotation(cacheable)")
//    public void logCacheAccess(JoinPoint joinPoint, Cacheable cacheable) {
//        System.out.println("Cacheable method accessed: Checking cache for device with ID.");
//        System.out.println("Cache name: " + cacheable.value());
//        Object[] args = joinPoint.getArgs();
//        if (args.length > 0) {
//            System.out.println("Device ID: " + args[0]);
//        }
//    }

//    @Before("cachePutMethods() && @annotation(cachePut)")
//    public void logCachePutAccess(JoinPoint joinPoint, CachePut cachePut) throws Exception{
//        System.out.println("CachePut method accessed: Updating cache for device.");
//        System.out.println("Cache name: " + cachePut.value());
//        Object[] args = joinPoint.getArgs();
//        if (args.length > 0) {
//            System.out.println("Device ID to be cached: " + args[0].getClass().getMethod("getId").invoke(args[0]));
//        }
//    }

    @Before("execution(* com.example.task3.repositories.DeviceRepo.findById(..))")
    public void logBeforeFindById(JoinPoint joinPoint) {
        System.out.println("Calling DeviceRepository.findById method with arguments: ");
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            System.out.println("Device ID: " + args[0]);
        }
    }
}
