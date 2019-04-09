package com.ego.passport.service;

import com.ego.commons.domain.EgoResult;
import com.ego.domain.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户服务接口
 * @author boge.peng
 * @create 2019-03-17 10:55
 */
public interface TbUserService {
    /**
     * 根据用户名和密码查询用户信息
     * @param user
     * @return
     */
    TbUser selectByUser(TbUser user);

    /**
     * 用户登陆
     * @param user
     * @return
     */
    EgoResult login(TbUser user, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    EgoResult getUserInfoByToken(String token);

    /**
     * 退出用户登陆
     * @param token
     * @param request
     * @param response
     * @return
     */
    EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response);
}
