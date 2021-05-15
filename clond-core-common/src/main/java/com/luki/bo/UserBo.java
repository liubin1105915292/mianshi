package com.luki.bo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * auther win
 * create 2021/5/14 0014 21:17
 **/
@Data
@TableName("luki_user")
public class UserBo implements Serializable {
    /**
     * 用户ID
     *
     */
    @TableId(value = "user_id",type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message="用户名不能为空")
    @Size(min = 2,max = 20,message = "用户名长度要在2-20之间")
    private String userName;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 邮箱
     */
    @NotBlank(message="邮箱不能为空")
    @Email(message="邮箱格式不正确")
    private String email;

    /**
     * 手机号
     */
    @Pattern(regexp="0?1[0-9]{10}",message = "请输入正确的手机号")
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 用户所在店铺id
     */
    private Long shopId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date create_time;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;
    /**
     * 逻辑删除 0正常 1删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private int del_flag;
}
