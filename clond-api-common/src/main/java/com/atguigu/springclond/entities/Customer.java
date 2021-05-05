package com.atguigu.springclond.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * auther win
 * create 2021/5/5 0005 12:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
    private Integer cus_num;
    private String company;
    private Integer dep_code;
    private Long money;

}
