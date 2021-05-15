package com.luki.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luki.bo.UserBo;
import com.luki.annotation.SysLog;
import com.luki.mapper.UserMapper;
import com.luki.util.Base64Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * auther win
 * create 2021/5/14 0014 21:08
 **/
@RestController
@RequestMapping("user")
public class ValidationController {
    @Resource
    private UserMapper userMapper;

    @PostMapping("save")
    @SysLog("新增用户")
    public ResponseEntity<String> save(@Valid @RequestBody UserBo user){
        try {
            String username = user.getUserName();
            UserBo userBo = userMapper.selectOne(new LambdaQueryWrapper<UserBo>()
                    .eq(UserBo::getUserName, username));
            if (userBo!=null) {
                return ResponseEntity.badRequest().body("该用户已存在");
            }
            user.setPassword(Base64Utils.encode(user.getPassword()));
            userMapper.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("query")
    @SysLog("查询所有用户")
    public ResponseEntity<List<UserBo>> query(){
        List<UserBo> userBos = null;
        //userBos = userMapper.selectList(new QueryWrapper<UserBo>().in("user_id",1));
        userBos = userMapper.selectList(null);
        return  ResponseEntity.status(200).body(userBos);
    }

    @SysLog("删除操作")
    @PostMapping("del/{id}")
    public ResponseEntity<String>  deleteById(@PathVariable("id") String id){
        int i = userMapper.deleteById(id);
        if(i>0){
            return  ResponseEntity.status(200).build();
        }else {
            return  ResponseEntity.status(400).build();
        }
    }
}

