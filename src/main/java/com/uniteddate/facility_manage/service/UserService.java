package com.uniteddate.facility_manage.service;

import com.uniteddate.facility_manage.entity.User;
import java.util.List;

/**
 * 用户表 (TbUser)表服务接口
 *
 * @author makejava
 * @since 2020-08-19 14:07:58
 */
public interface UserService {

    /**
     * 通过账户查询单条数据
     *
     * @param  account
     * @return 实例对象
     */
    User queryByAccount(String account);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    Boolean insert (User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    Boolean update(User user);

    /**
     * 通过主键删除数据
     *
     * @param  id
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过主键删除数据
     *
     * @param  id
     * @return 实例对象
     */
    User queryById(Long id);
}