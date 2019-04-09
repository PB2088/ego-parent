package com.ego.manage.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * 图片上传服务接口
 * @author boge.peng
 * @create 2019-03-12 15:41
 */
public interface PictureService {
    /**
     * 图片上传
     * @param file
     * @return
     */
    Map<String,Object> upload(MultipartFile file) throws IOException;
}
