package com.ego.domain;

import java.io.Serializable;
import java.util.Date;

public class TbItemParamItem implements Serializable {
    private static final long serialVersionUID = 7391564278740183451L;

    private Long id;

    private Long itemId;

    private Date created;

    private Date updated;

    private String paramData;

    public static final TbItemParamItem NULL = new TbItemParamItem();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData == null ? null : paramData.trim();
    }
}