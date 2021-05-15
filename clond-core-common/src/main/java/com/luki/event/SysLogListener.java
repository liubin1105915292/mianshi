package com.luki.event;

import com.luki.bo.SysLogBo;
import com.luki.mapper.LogMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * auther win
 * create 2021/5/15 0015 20:28
 **/
@Slf4j
@AllArgsConstructor
@Component
public class SysLogListener {
    @Resource
    private final LogMapper logMapper;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveLog(SysLogEvent sysLogEvent){
        log.info("异步插入日志");
        SysLogBo sysLogBo = (SysLogBo) sysLogEvent.getSource();
        logMapper.insert(sysLogBo);
    }
}
