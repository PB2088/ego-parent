package com.ego.passport.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cache.dao.JedisClusterDao;
import com.ego.commons.domain.EgoResult;
import com.ego.commons.utils.CommonFunctions;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.domain.TbUser;
import com.ego.dubbo.service.TbUserDubboService;
import com.ego.passport.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 用户服务实现类
 *
 * @author boge.peng
 * @create 2019-03-17 10:56
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Reference
    private TbUserDubboService userDubboService;

    @Autowired
    private JedisClusterDao jedisClusterDao;

    @Override
    public TbUser selectByUser(TbUser user) {
        return userDubboService.selectByUser(user);
    }

    @Override
    public EgoResult login(TbUser user, HttpServletRequest request, HttpServletResponse response) {
        TbUser tbUser = selectByUser(user);

        if (CommonFunctions.isEmpty(tbUser)) {
            return EgoResult.FAILED.setMsg("用户名或密码错误!");
        }

        String tokenValue = UUID.randomUUID().toString().toUpperCase();
        jedisClusterDao.set(tokenValue, JsonUtils.objectToJson(tbUser));
        //设置key的过期时间为7天
        jedisClusterDao.expire(tokenValue, 60 * 60 * 24 * 7);

        CookieUtils.setCookie(request, response, "TT_TOKEN", tokenValue, 60 * 60 * 24 * 7);

        return EgoResult.SUCCESS;
    }

    @Override
    public EgoResult getUserInfoByToken(String token) {
        if (jedisClusterDao.exists(token)) {
            String userInfoJson = jedisClusterDao.get(token);
            if (CommonFunctions.notEmpty(userInfoJson)) {
                TbUser user = JsonUtils.jsonToPojo(userInfoJson, TbUser.class);
                user.setPassword(null);
                return EgoResult.SUCCESS.setData(user);
            }
        }

        return EgoResult.FAILED.setMsg("获取用户失败!");
    }

    @Override
    public EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response) {
        jedisClusterDao.del(token);
        CookieUtils.deleteCookie(request,response,"TT_TOKEN");

        return EgoResult.SUCCESS.setMsg("OK");
    }
}
