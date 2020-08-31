package com.uniteddate.facility_manage.service;

import com.uniteddate.facility_manage.entity.FacilityInfo;
import com.uniteddate.facility_manage.entity.UserFacility;
import java.util.List;

/**
 * 用户设备关联表 (TbUserFacility)表服务接口
 *
 * @author makejava
 * @since 2020-08-19 14:07:58
 */
public interface UserFacilityService {

        /**
         * 通过ID查询单条数据
         *
         * @param  id
         * @return 实例对象
         */
        UserFacility queryById(Long id);

        /**
         * 新增数据
         *
         * @param userFacility 实例对象
         * @return 实例对象
         */
        UserFacility insert(UserFacility userFacility);

        /**
         * 修改数据
         *
         * @param tbUserFacility 实例对象
         * @return 实例对象
         */
        UserFacility update(UserFacility tbUserFacility);

        /**
         * 通过主键删除数据
         *
         * @param  id
         * @return 是否成功
         */
        boolean deleteById(Long id);

        /**
         * 通过用户ID查询
         *
         * @param  userId
         * @return 用户设备关联信息列表
         */
        List<FacilityInfo> queryListByUserId(Long userId);

        List<UserFacility> queryByuserIdAndfacilityId(Long userId, Long facilityId);
}