package com.ego.cart.interceptor;

import com.ego.commons.domain.EgoResult;
import com.ego.commons.utils.CommonFunctions;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author boge.peng
 * @create 2019-03-17 15:57
 */
public class LoginInterceptor implements HandlerInterceptor {

    private String passportUrl;

    public void setPassportUrl(String passportUrl) {
        this.passportUrl = passportUrl;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws
            Exception {

        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        if (CommonFunctions.notEmpty(token)) {
            String json = HttpClientUtil.doPost(passportUrl + token);
            EgoResult result = JsonUtils.jsonToPojo(json, EgoResult.class);
            if (result.getStatus() == 200) {
                return true;
            }
        }
        String num = request.getParameter("num");
        response.sendRedirect("http://localhost:8084/user/showLogin?interurl="+request.getRequestURL()+"%3Fnum="+num);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
