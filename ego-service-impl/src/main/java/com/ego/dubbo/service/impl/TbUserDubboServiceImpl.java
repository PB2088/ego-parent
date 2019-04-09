package com.ego.dubbo.service.impl;

import com.ego.commons.utils.CommonFunctions;
import com.ego.domain.TbUser;
import com.ego.domain.TbUserExample;
import com.ego.dubbo.service.TbUserDubboService;
import com.ego.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户服务实现类
 *
 * @author boge.peng
 * @create 2019-03-17 10:48
 */
public class TbUserDubboServiceImpl implements TbUserDubboService {

    @Autowired
    private TbUserMapper userMapper;

    @Override
    public TbUser selectByUser(TbUser user) {
        TbUserExample example = new TbUserExample();
        example.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());

        List<TbUser> users = userMapper.selectByExample(example);

        return CommonFunctions.notEmpty(users) ? users.get(0) : null;
    }
}
