package com.uniteddate.facility_manage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 设备信息 (TbFacilityInfo)实体类
 *
 * @author makejava
 * @since 2020-08-19 14:07:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value="FacilityInfo",description="设备信息对象")
public class FacilityInfo implements Serializable {
    private static final long serialVersionUID = 385568687869626389L;
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
    * IP
    */
    @ApiModelProperty(value="ip",name="ip",required=true)
    private String ip;

    /**
    * 地址
    */
    @ApiModelProperty(value="地址",name="address",required=true)
    private String address;

    /**
    * X轴坐标
    */
    @ApiModelProperty(value="X轴坐标",name="coordinateX",required=true)
    private String coordinateX;

    /**
    * Y轴坐标
    */
    @ApiModelProperty(value="Y轴坐标",name="coordinateY",required=true)
    private String coordinateY;

    /**
    * Z轴坐标
    */
    @ApiModelProperty(value="Z轴坐标",name="coordinateZ",required=true)
    private String coordinateZ;

    /**
    * 角度
    */
    @ApiModelProperty(value="角度",name="angle",required=true)
    private String angle;

}