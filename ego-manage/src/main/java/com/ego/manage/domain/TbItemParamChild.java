package com.ego.manage.domain;

import com.ego.domain.TbItemParam;

/**
 * @author boge.peng
 * @create 2019-03-13 10:38
 */
public class TbItemParamChild extends TbItemParam {
    private String itemCatName;

    public String getItemCatName() {
        return itemCatName;
    }

    public void setItemCatName(String itemCatName) {
        this.itemCatName = itemCatName;
    }
}
