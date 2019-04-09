package com.ego.commons.domain;

import com.ego.domain.TbItem;

/**
 * @author boge.peng
 * @create 2019-03-16 10:48
 */
public class TbItemExtend extends TbItem {
    private String[] images;

    private Integer stock;

    private boolean enough;

    public boolean isEnough() {
        return enough;
    }

    public void setEnough(boolean enough) {
        this.enough = enough;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
