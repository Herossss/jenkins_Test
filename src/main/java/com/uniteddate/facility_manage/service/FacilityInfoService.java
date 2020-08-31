package com.uniteddate.facility_manage.service;

import com.uniteddate.facility_manage.entity.FacilityInfo;
import java.util.List;

/**
 * 设备信息 (TbFacilityInfo)表服务接口
 *
 * @author makejava
 * @since 2020-08-19 14:07:56
 */
public interface FacilityInfoService {

    /**
     * 新增数据
     *
     * @param facilityInfo 实例对象
     * @return 实例对象
     */
    FacilityInfo insert(FacilityInfo facilityInfo);

    /**
     * 修改数据
     *
     * @param facilityInfo 实例对象
     * @return 实例对象
     */
    Boolean update(FacilityInfo facilityInfo);

    /**
     * 通过主键删除数据
     *
     * @param  id
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过设备ip查询设备信息
     *
     * @param  ip
     * @return 设备信息
     */
    FacilityInfo queryByIpAndUserId(String ip,Long userId);
}