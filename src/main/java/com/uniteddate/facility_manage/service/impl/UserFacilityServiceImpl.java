package com.uniteddate.facility_manage.service.impl;

import com.uniteddate.facility_manage.dao.FacilityInfoMapper;
import com.uniteddate.facility_manage.entity.FacilityInfo;
import com.uniteddate.facility_manage.entity.User;
import com.uniteddate.facility_manage.entity.UserFacility;
import com.uniteddate.facility_manage.dao.UserFacilityMapper;
import com.uniteddate.facility_manage.service.FacilityInfoService;
import com.uniteddate.facility_manage.service.UserFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 用户设备关联表 (TbUserFacility)表服务实现类
 *
 * @author makejava
 * @since 2020-08-19 14:07:58
 */
@Service("UserFacilityService")
public class UserFacilityServiceImpl implements UserFacilityService {

    @Autowired
    private UserFacilityMapper userFacilityMapper;
    @Autowired
    private FacilityInfoMapper facilityInfoMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param  id
     * @return 实例对象
     */
    @Override
    public UserFacility queryById(Long id) {
        return this.userFacilityMapper.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param userFacility 实例对象
     * @return 实例对象
     */
    @Override
    public UserFacility insert(UserFacility userFacility) {
        this.userFacilityMapper.insert(userFacility);
        return userFacility;
    }

    /**
     * 修改数据
     *
     * @param userFacility 实例对象
     * @return 实例对象
     */
    @Override
    public UserFacility update(UserFacility userFacility) {
        this.userFacilityMapper.update(userFacility);
        return this.queryById(userFacility.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param  id
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userFacilityMapper.deleteById(id) > 0;
    }

    /**
     * 通过用户ID查询设备信息列表
     *
     * @param  userId
     * @return 设备信息列表
     */
    @Override
    public List<FacilityInfo> queryListByUserId(Long userId) {
        List<FacilityInfo> facilityInfos = new LinkedList<>();
        UserFacility userFacility = UserFacility.builder()
                .userId(userId)
                .build();
        List<UserFacility> userFacilities = userFacilityMapper.queryAll(userFacility);
        if(userFacilities != null && userFacilities.size() != 0){
            for(UserFacility userFacilityItem : userFacilities){
                FacilityInfo facilityInfo = facilityInfoMapper.queryById(userFacilityItem.getFacilityId());
                facilityInfos.add(facilityInfo);
            }
        }
        return facilityInfos;
    }

    @Override
    public List<UserFacility> queryByuserIdAndfacilityId(Long userId, Long facilityId) {
        UserFacility userFacility = UserFacility.builder()
                .userId(userId)
                .facilityId(facilityId)
                .build();
        List<UserFacility> userFacilities = userFacilityMapper.queryAll(userFacility);
        return userFacilities;
    }
}