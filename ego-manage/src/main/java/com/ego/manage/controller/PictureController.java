package com.ego.manage.controller;

import com.ego.commons.constant.RequestMappingConstant;
import com.ego.commons.utils.JsonUtils;
import com.ego.manage.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * 图片上传
 * @author boge.peng
 * @create 2019-03-12 15:36
 */
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = RequestMappingConstant.Manage.PIC_UPLOAD,produces = "text/plan;charset=UTF-8")
    @ResponseBody
    public String upload(MultipartFile uploadFile) {
        Map<String, Object> map= null;
        try {
            map = pictureService.upload(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("message", "图片上服务器异常!"+e.getMessage());
        }

        return JsonUtils.objectToJson(map);
    }
}
