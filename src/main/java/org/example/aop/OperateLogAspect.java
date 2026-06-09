package org.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.pojo.OperateLog;
import org.example.mapper.OperateLogMapper;
import org.example.util.CurrentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class OperateLogAspect {
    
    @Autowired
    private OperateLogMapper operateLogMapper;
    
    /**
     * 环绕通知：记录操作日志
     */
    @Around("@annotation(org.example.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        // 开始时间
        long startTime = System.currentTimeMillis();

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 计算执行耗时
        long costTime = System.currentTimeMillis() - startTime;

        // 保存日志
        OperateLog operateLog = new OperateLog();

        // 操作时间
        operateLog.setOperateTime(LocalDateTime.now());

        // 执行耗时
        operateLog.setCostTime(costTime);

        // 获取目标类的全类名
        String className = joinPoint.getTarget().getClass().getName();
        operateLog.setClassName(className);

        // 获取目标方法的方法名
        String methodName = joinPoint.getSignature().getName();
        operateLog.setMethodName(methodName);

        // 获取方法参数（转换为字符串）
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));

        //获取返回值
        operateLog.setReturnValue(result != null ? result.toString() : "void");

        //获取操作人ID
        operateLog.setOperateEmpId(getCurrentUserId());

        // 保存日志
        log.info("记录操作日志：{}", operateLog);
        operateLogMapper.insert(operateLog);

        return result;
    }


    /**
     * 获取当前操作用户ID
     * 根据实际项目情况实现，这里提供几种常见方式
     */
    private Integer getCurrentUserId() {

        return CurrentHolder.getCurrentId();
    }
}