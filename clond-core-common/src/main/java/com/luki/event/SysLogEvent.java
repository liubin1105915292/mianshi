package com.luki.event;

import com.luki.bo.SysLogBo;
import org.springframework.context.ApplicationEvent;

/**
 * auther win
 * create 2021/5/15 0015 20:29
 **/

public class SysLogEvent extends ApplicationEvent {
    public SysLogEvent(SysLogBo source) {
        super(source);
    }
}
