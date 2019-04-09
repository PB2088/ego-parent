package com.ego.passport.controller;

import com.ego.commons.constant.RequestMappingConstant;
import com.ego.commons.domain.EgoResult;
import com.ego.commons.utils.CommonFunctions;
import com.ego.domain.TbUser;
import com.ego.passport.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * 登陆控制器
 *
 * @author boge.peng
 * @create 2019-03-17 10:39
 */
@Controller
public class TbUserController {

    @Autowired
    private TbUserService userService;

    @RequestMapping(RequestMappingConstant.Passport.USER_SHOW_LOGIN)
    public String showLogin(@RequestHeader(value = "Referer", defaultValue = "") String url, Map model, String interurl) {
        if (CommonFunctions.notEmpty(interurl)) {
            model.put("redirect", interurl);
        } else if (CommonFunctions.notEmpty(url)){
            model.put("redirect", url);
        }
        return "login";
    }

    @RequestMapping(RequestMappingConstant.Passport.USER_LOGIN)
    @ResponseBody
    public EgoResult login(TbUser user, HttpServletRequest request, HttpServletResponse response) {
        return userService.login(user, request, response);
    }

    @RequestMapping(RequestMappingConstant.Passport.USER_TOKEN)
    @ResponseBody
    public Object getUserInfo(@PathVariable String token, String callback) {
        EgoResult er = userService.getUserInfoByToken(token);
        if (CommonFunctions.notEmpty(callback)) {
            MappingJacksonValue mv = new MappingJacksonValue(er);
            mv.setJsonpFunction(callback);
            return mv;
        }

        return er;
    }

    @RequestMapping(RequestMappingConstant.Passport.USER_LOGOUT)
    @ResponseBody
    public Object logout(@PathVariable String token, String callback, HttpServletRequest request, HttpServletResponse response) {
        EgoResult er = userService.logout(token, request, response);

        if (CommonFunctions.notEmpty(token)) {
            MappingJacksonValue mv = new MappingJacksonValue(er);
            mv.setJsonpFunction(callback);
            return mv;
        }

        return er;
    }
}
