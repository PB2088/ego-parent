package com.ego.item.controller;

import com.ego.commons.constant.RequestMappingConstant;
import com.ego.item.domain.PortalMen;
import com.ego.item.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author boge.peng
 * @create 2019-03-13 21:48
 */
@Controller
public class TbItemCatController {

    @Autowired
    private TbItemCatService itemCatService;

    @RequestMapping(RequestMappingConstant.Item.REST_ITEMCAT_ALL)
    @ResponseBody
    public MappingJacksonValue showPortalMenu(String callback) {
        PortalMen portalMen = itemCatService.showPortalMenu();

        MappingJacksonValue jacksonValue = new MappingJacksonValue(portalMen);
        jacksonValue.setJsonpFunction(callback);

        return jacksonValue;
    }

}
