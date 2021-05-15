package com.luki.bo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * auther win
 * create 2021/5/15 0015 19:53
 **/
@TableName("luki_log")
@Data
public class SysLogBo {
    @TableId(type = IdType.AUTO)
    /*主键*/
    private Long log_id;
    /*入参*/
    private String in_parameters;
    /*出参*/
    private String out_parameters;
    /*调用类*/
    private String call_class;
    /*调用方法*/
    private String call_method;
    /*方法注解*/
    private String call_remark;
    /*创建时间*/
    @TableField(fill = FieldFill.INSERT)
    private Date create_time;
    /*调用时间*/
    private Long call_time;
    /*ip*/
    private String ip;
    /*调用url*/
    private String url;

}
