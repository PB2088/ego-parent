package com.ego.item.domain;

import com.ego.commons.constant.RequestMappingConstant;

import java.io.Serializable;
import java.util.List;

/**
 * Portal菜单的数据格式
 * @author boge.peng
 * @create 2019-03-13 17:03
 */
public class PortalMen implements Serializable{

    private static final long serialVersionUID = -1139474245850552172L;

    private List<Object> data;

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
