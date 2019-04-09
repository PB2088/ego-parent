package com.ego.dubbo.service;

import com.ego.domain.TbUser;

/**
 * 用户服务
 * @author boge.peng
 * @create 2019-03-17 10:47
 */
public interface TbUserDubboService {
    /**
     * 根据用户和密码查询用户信息
     * @param user
     * @return
     */
    TbUser selectByUser(TbUser user);
}
