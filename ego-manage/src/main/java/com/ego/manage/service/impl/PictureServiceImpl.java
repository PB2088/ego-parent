package com.ego.manage.service.impl;

import com.ego.commons.utils.FtpUtil;
import com.ego.commons.utils.IDUtils;
import com.ego.manage.service.PictureService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author boge.peng
 * @create 2019-03-12 15:41
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Value("${ftpclient.host}")
    private String host;

    @Value("${ftpclient.port}")
    private int port;

    @Value("${ftpclient.username}")
    private String userName;

    @Value("${ftpclient.password}")
    private String password;

    @Value("${ftpclient.basepath}")
    private String basePath;

    @Value("${ftpclient.filepath}")
    private String filePath;

    //TODO
    //private static Logger logger = Logger.getLogger(PictureServiceImpl.class);

    @Override
    public Map<String, Object> upload(MultipartFile file) throws IOException {
        String filename = IDUtils.genImageName() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        boolean result = FtpUtil.uploadFile(host, port, userName, password, basePath, filePath, filename, file
                .getInputStream());

        Map<String, Object> map = new HashMap<>();
        if (result) {
            map.put("error", 0);
            map.put("url", "http://" + host +  filePath + "/" + filename);
        } else {
            map.put("error", 1);
            map.put("message", "图片上失败");
        }

        return map;
    }
}
