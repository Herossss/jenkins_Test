package com.uniteddate.facility_manage.service.impl;

import com.uniteddate.facility_manage.entity.FacilityInfo;
import com.uniteddate.facility_manage.dao.FacilityInfoMapper;
import com.uniteddate.facility_manage.service.FacilityInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备信息 (TbFacilityInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-08-19 14:07:56
 */
@Service("tbFacilityInfoService")
public class FacilityInfoServiceImpl implements FacilityInfoService {
    @Resource
    private FacilityInfoMapper facilityInfoMapper;


    /**
     * 新增数据
     *
     * @param facilityInfo 实例对象
     * @return 实例对象
     */
    @Override
    public FacilityInfo insert(FacilityInfo facilityInfo) {
        this.facilityInfoMapper.insert(facilityInfo);
        return facilityInfo;
    }

    /**
     * 修改数据
     *
     * @param facilityInfo 实例对象
     * @return 实例对象
     */
    @Override
    public Boolean update(FacilityInfo facilityInfo) {
        int update = this.facilityInfoMapper.update(facilityInfo);
        return update >0;
    }

    /**
     * 通过主键删除数据
     *
     * @param  id
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.facilityInfoMapper.deleteById(id) > 0;
    }

    @Override
    public FacilityInfo queryByIpAndUserId(String ip,Long userId) {
        FacilityInfo facilityInfo = facilityInfoMapper.queryByIpAndUserId(ip,userId);
        return facilityInfo;
    }
}