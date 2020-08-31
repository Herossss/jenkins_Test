package com.uniteddate.facility_manage.service.impl;

import com.uniteddate.facility_manage.entity.User;
import com.uniteddate.facility_manage.dao.UserMapper;
import com.uniteddate.facility_manage.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表 (User)表服务实现类
 *
 * @author makejava
 * @since 2020-08-19 14:07:58
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 通过账户查询单条数据
     *
     * @param  account 账户名
     * @return 实例对象
     */
    @Override
    public User queryByAccount(String account) {
        User user = User.builder()
                .userAccount(account)
                .build();
        List<User> users = userMapper.queryAll(user);
        if(users != null && users.size() != 0){
            return users.get(0);
        }
        return null;
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean insert(User user) {
        Integer insert = this.userMapper.insert(user);
        return insert > 0;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean update(User user) {
        int update = this.userMapper.update(user);
        return update > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param  id
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userMapper.deleteById(id) > 0;
    }

    @Override
    public User queryById(Long id) {
        User user = userMapper.queryById(id);
        return user;
    }
}