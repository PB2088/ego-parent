package com.ego.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author boge.peng
 * @create 2019-03-13 16:49
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String index() {
        return "forward:/showBigPic";
    }
}
