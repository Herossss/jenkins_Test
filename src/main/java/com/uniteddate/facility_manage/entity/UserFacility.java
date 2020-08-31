package com.uniteddate.facility_manage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户设备关联表 (TbUserFacility)实体类
 *
 * @author makejava
 * @since 2020-08-19 14:07:58
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFacility implements Serializable {
    private static final long serialVersionUID = 409184446881981929L;
    /**
    * 主键ID
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
    * 用户ID
    */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    /**
    * 设备ID
    */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long facilityId;

}