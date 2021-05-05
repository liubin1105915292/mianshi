package com.atguigu.springclond.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T date;
    private Boolean isExist;

    public CommonResult(Integer code, String message,T date) {
        this(code,message,date,false);
    }

    public CommonResult(Integer code, String message) {
        this(code,message,null,false);
    }
}
