package com.uniteddate.facility_manage.dao;

import com.uniteddate.facility_manage.entity.FacilityInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 设备信息 (FacilityInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-19 14:07:55
 */
@Mapper
public interface FacilityInfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param  id
     * @return 实例对象
     */
    FacilityInfo queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<FacilityInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param facilityInfo 实例对象
     * @return 对象列表
     */
    List<FacilityInfo> queryAll(FacilityInfo facilityInfo);

    /**
     * 新增数据
     *
     * @param facilityInfo 实例对象
     * @return 影响行数
     */
    int insert(FacilityInfo facilityInfo);

    /**
     * 修改数据
     *
     * @param facilityInfo 实例对象
     * @return 影响行数
     */
    int update(FacilityInfo facilityInfo);

    /**
     * 通过主键删除数据
     *
     * @param  id
     * @return 影响行数
     */
    int deleteById(Long id);

    FacilityInfo queryByIpAndUserId(@Param(value = "ip") String ip,@Param(value = "userId") Long userId);
}