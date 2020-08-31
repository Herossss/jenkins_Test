package com.uniteddate.facility_manage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户表 (TbUser)实体类
 *
 * @author makejava
 * @since 2020-08-19 14:07:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = -52138076919127649L;
    /**
    * 用户ID
    */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
    * 记录创建时间
    */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createTime;
    /**
    * 记录更新时间
    */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateTime;
    /**
    * 用户名
    */
    private String userName;
    /**
    * 账号
    */
    private String userAccount;
    /**
    * 密码
    */
    private String userPassword;

}