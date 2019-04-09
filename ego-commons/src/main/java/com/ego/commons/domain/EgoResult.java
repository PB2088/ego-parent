package com.ego.commons.domain;

import java.util.HashMap;
import java.util.Map;

public class EgoResult {
    public static final EgoResult SUCCESS = new EgoResult(200,"处理成功");

    public static final EgoResult FAILED = new EgoResult(-99,"处理失败");

	private int status;

	private String message;

	private Map<String,Object> dataMap = new HashMap<>();

	private Object data;

	private String msg;

	public EgoResult() {

    }

    public EgoResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMsg() {
        return msg;
    }

    public EgoResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public EgoResult addData(String key,Object obj) {
        this.dataMap.put(key,obj);
        return this;
    }

    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

    public Object getData() {
        return data;
    }

    public EgoResult setData(Object data) {
        this.data = data;
        return this;
    }
}
