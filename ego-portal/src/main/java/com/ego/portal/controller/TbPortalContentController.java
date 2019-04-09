package com.ego.portal.controller;

import com.ego.portal.service.TbPortalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author boge.peng
 * @create 2019-03-14 22:12
 */
@Controller
public class TbPortalContentController {

    @Autowired
    private TbPortalContentService tbPortalContentService;

    @RequestMapping("showBigPic")
    public String showBigPic(Map<String,Object> model) {
        model.put("ad1",tbPortalContentService.showBigPic());
        System.out.println("nginx负载均衡测试点..........啊哈哈");
        return "index";
    }
}
