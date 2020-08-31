package com.uniteddate.facility_manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.uniteddate.facility_manage.common.bean.ResultBean;
import com.uniteddate.facility_manage.common.constant.Constants;
import com.uniteddate.facility_manage.common.service.token.TokenService;
import com.uniteddate.facility_manage.common.util.date.DateUtil;
import com.uniteddate.facility_manage.common.util.id.IdGenetatorUtil;
import com.uniteddate.facility_manage.common.util.md5.CheckAndValidateUtil;
import com.uniteddate.facility_manage.common.util.token.JWTUtils;
import com.uniteddate.facility_manage.entity.User;
import com.uniteddate.facility_manage.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 用户表 (TbUser)表控制层
 *
 * @author makejava
 * @since 2020-08-19 14:07:58
 */
@RestController
@RequestMapping("/user")
@Api("用户相关")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="account",dataType="String",required=true,value="用户的账号"),
            @ApiImplicitParam(paramType="query",name="password",dataType="String",required=true,value="用户的密码")
    })
    @PostMapping("/register")
    public ResultBean register(@RequestParam(value = "account",required = true) String account,
                               @RequestParam(value = "password",required = true) String password) {
        //检查账号是否已经存在
        if(userService.queryByAccount(account) != null){
            return ResultBean.defeated("账户已存在");
        }

        //插入数据
        Long time = Timestamp.valueOf(LocalDateTime.now()).getTime();
        User user = User.builder()
                .id(IdGenetatorUtil.getSnowFlakeId())
                .createTime(time)
                .updateTime(time)
                .userAccount(account)
                .userPassword(CheckAndValidateUtil.md5(password))
                .build();
        Boolean insert = userService.insert(user);
        return insert ? ResultBean.succeed() : ResultBean.defeated();
    }

    /**
     * 通过账号密码登录
     *
     * @param account password 账号密码
     * @return 登录令牌
     */
    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="account",dataType="String",required=true,value="用户的账号"),
            @ApiImplicitParam(paramType="query",name="password",dataType="String",required=true,value="用户的密码")
    })
    @PostMapping("/signIn")
    public ResultBean signIn(@RequestParam(value = "account",required = true) String account,
                             @RequestParam(value = "password",required = true) String password) throws Exception {
        //检查账号密码是否正确
        User user = userService.queryByAccount(account);
        if(user == null){
            return ResultBean.defeated("无此账户");
        }else if(!CheckAndValidateUtil.md5(password).equals(user.getUserPassword())){
            return ResultBean.defeated("账户密码错误");
        }

        //返回token
        Map<String, Object> payLoad = new JSONObject(true);
        payLoad.put("userId", user.getId());
        payLoad.put("userAccount", user.getUserAccount());
        String token = TokenService.createToken(user.getId().toString(), payLoad, Constants.SECONDS_IN_DAY);
        return ResultBean.succeed(token);
    }

    @ApiOperation("修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "userName",dataType = "String",required = true,value = "用户名"),
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "登录令牌")
    })
    @PutMapping("/updateUserInfo")
    public ResultBean updateUserInfo(@RequestParam(value = "userName",required = true) String userName,
                                     @RequestHeader(value = "token",required = true) String token){
        Long userId = TokenService.getUserId(token);
        User user = User.builder()
                .id(userId)
                .updateTime(DateUtil.getNow())
                .userName(userName)
                .build();
        Boolean update = userService.update(user);
        return update ? ResultBean.succeed() : ResultBean.defeated();
    }

    @ApiOperation("修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "password",dataType = "String",required = true,value = "密码"),
            @ApiImplicitParam(paramType = "query",name = "newPassword",dataType = "String",required = true,value = "新密码"),
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "登录令牌")
    })
    @PostMapping("/modifyPassword")
    public ResultBean modifyPassword(@RequestParam(value = "password",required = true) String password,
                                     @RequestParam(value = "newPassword",required = true)String newPassword,
                                     @RequestHeader(value = "token",required = true)String token){
        Long userId = TokenService.getUserId(token);

        User user = userService.queryById(userId);
        if(!CheckAndValidateUtil.md5(password).equals(user.getUserPassword())){
            return ResultBean.defeated("密码错误");
        }

        user.setUpdateTime(DateUtil.getNow());
        user.setUserPassword(CheckAndValidateUtil.md5(newPassword));

        Boolean update = userService.update(user);
        return update ? ResultBean.succeed() : ResultBean.defeated();
    }

    @ApiOperation("查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = "token",dataType = "String",required = true,value = "登录令牌")
    })
    @GetMapping("/getUserInfo")
    public ResultBean getUserInfo(@RequestHeader(value = "token",required = true)String token){
        Long userId = TokenService.getUserId(token);

        User user = userService.queryById(userId);
        user.setUserPassword(null);

        return ResultBean.succeed(user);
    }
}