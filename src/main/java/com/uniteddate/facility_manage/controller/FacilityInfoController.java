package com.uniteddate.facility_manage.controller;

import com.uniteddate.facility_manage.common.bean.ResultBean;
import com.uniteddate.facility_manage.common.service.token.TokenService;
import com.uniteddate.facility_manage.common.util.date.DateUtil;
import com.uniteddate.facility_manage.common.util.id.IdGenetatorUtil;
import com.uniteddate.facility_manage.entity.FacilityInfo;
import com.uniteddate.facility_manage.entity.UserFacility;
import com.uniteddate.facility_manage.service.FacilityInfoService;
import com.uniteddate.facility_manage.service.UserFacilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备信息 (TbFacilityInfo)表控制层
 *
 * @author makejava
 * @since 2020-08-19 14:07:57
 */
@RestController
@RequestMapping("/facility")
@Api("设备管理")
public class FacilityInfoController {
    @Autowired
    private FacilityInfoService facilityInfoService;
    @Autowired
    private UserFacilityService userFacilityService;

    @ApiOperation(value = "添加设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="登录令牌"),

    })
    @PutMapping("/insertFacilityInfo")
    @Transactional
    public ResultBean selectOneByIp(@RequestBody FacilityInfo facilityInfo,
                                    @RequestHeader(value = "token",required = true) String token) {
        Long userId = TokenService.getUserId(token);
        Long snowFlakeId = IdGenetatorUtil.getSnowFlakeId();
        Long now = DateUtil.getNow();

        facilityInfo.setId(snowFlakeId);
        facilityInfo.setCreateTime(now);
        facilityInfo.setUpdateTime(now);

        facilityInfoService.insert(facilityInfo);

        UserFacility userFacility = UserFacility.builder()
                .id(IdGenetatorUtil.getSnowFlakeId())
                .userId(userId)
                .facilityId(snowFlakeId)
                .createTime(now)
                .updateTime(now)
                .build();
        userFacilityService.insert(userFacility);
        return ResultBean.succeed(facilityInfo);
    }

    @ApiOperation(value = "修改设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="登录令牌")
    })
    @PutMapping("/updataFacilityInfo")
    public ResultBean updataFacilityInfo(@RequestBody FacilityInfo facilityInfo,
                                    @RequestHeader(value = "token",required = true) String token) {
        Long userId = TokenService.getUserId(token);
        //检查用户是否拥有该设备管理权
        List<UserFacility> userFacilities = userFacilityService.queryByuserIdAndfacilityId(userId, facilityInfo.getId());
        if(userFacilities == null || userFacilities.size() == 0){
            return ResultBean.defeated("用户无设备管理权限");
        }

        Boolean update = facilityInfoService.update(facilityInfo);
        return update ? ResultBean.succeed() : ResultBean.defeated();
    }

    @ApiOperation(value = "通过设备ID删除设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="登录令牌"),
            @ApiImplicitParam(paramType="query",name="facilityId",dataType="Long",required=true,value="设备ID"),
    })
    @DeleteMapping("deleteFacilityInfo")
    public ResultBean deleteFacilityInfo(@RequestParam(value = "facilityId",required = true) Long facilityId,
                                         @RequestHeader(value = "token",required = true) String token) {
        Long userId = TokenService.getUserId(token);
        //检查用户是否拥有该设备管理权
        List<UserFacility> userFacilities = userFacilityService.queryByuserIdAndfacilityId(userId, facilityId);
        if(userFacilities == null || userFacilities.size() == 0){
            return ResultBean.defeated("用户无设备管理权限");
        }

        boolean b = facilityInfoService.deleteById(facilityId);
        return b ? ResultBean.succeed() : ResultBean.defeated();
    }

    @ApiOperation(value = "通过IP查询设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="登录令牌"),
            @ApiImplicitParam(paramType="query",name="ip",dataType="String",required=true,value="ip"),
    })
    @GetMapping("selectOneByIp")
    public ResultBean selectOneByIp(@RequestParam(value = "ip",required = true) String ip,
                                    @RequestHeader(value = "token",required = true) String token) {
        Long userId = TokenService.getUserId(token);
        FacilityInfo facilityInfo =  facilityInfoService.queryByIpAndUserId(ip,userId);
        return ResultBean.succeed(facilityInfo);
    }


    @ApiOperation(value = "查询用户设备信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="登录令牌")
    })
    @GetMapping("/selectUserAllFacility")
    public ResultBean selectOne(@RequestHeader(value = "token",required = true) String token) {

        Long userId = TokenService.getUserId(token);

        //获取设备信息
        List<FacilityInfo> facilityInfos =  userFacilityService.queryListByUserId(userId);

        return ResultBean.succeed(facilityInfos);
    }
}