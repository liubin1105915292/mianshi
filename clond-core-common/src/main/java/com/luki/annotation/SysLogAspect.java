package com.luki.annotation;

import com.google.gson.Gson;
import com.luki.bo.SysLogBo;
import com.luki.event.SysLogEvent;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * auther win
 * create 2021/5/13 0013 22:49
 **/
@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class SysLogAspect {

    /** 换行符 */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final ApplicationEventPublisher publisher;

    /** 以自定义 @WebLog 注解为切点 */
    @Pointcut("@annotation(com.luki.annotation.SysLog)")
    public void webLog() {}

    /**
     * 在切点之前织入
     *@ joinPoint
     *@ Throwable
     */
  /*  @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印描述信息
        log.info("Description    : {}", methodDescription);
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args   : {}", new Gson().toJson(joinPoint.getArgs()));
    }


    @After("webLog()")
    public void doAfter() throws Throwable {
        // 接口结束后换行，方便分割查看
        log.info("=========================================== End ===========================================" + LINE_SEPARATOR);
    }
*/

    @Around("@annotation(syslog)")
    @SneakyThrows
    public Object doAround(ProceedingJoinPoint point,SysLog syslog) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        SysLogBo sysLogBo = new SysLogBo();
        sysLogBo.setUrl(url);
        sysLogBo.setIp(ip);
        long startTime = System.currentTimeMillis();
        Object[] args = point.getArgs();

        sysLogBo.setIn_parameters(new Gson().toJson(args));//入参参数
        sysLogBo.setCall_remark(syslog.value());//方法注解
        String className = point.getTarget().getClass().getSimpleName();
        sysLogBo.setCall_class(className); //调用类名
        String methodName = point.getSignature().getName();//调用类名
        sysLogBo.setCall_method(methodName);

        Object result = point.proceed();

        sysLogBo.setOut_parameters(new Gson().toJson(result)); //调用结果
        long endTime = System.currentTimeMillis();
        sysLogBo.setCall_time(endTime-startTime); //调用时间
        publisher.publishEvent(new SysLogEvent(sysLogBo));
        return result;
    }


    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(SysLog.class).value());
                    break;
                }
            }
        }
        return description.toString();
    }

}
