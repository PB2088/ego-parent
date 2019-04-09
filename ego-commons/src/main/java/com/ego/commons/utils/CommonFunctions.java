package com.ego.commons.utils;

import java.util.List;

/**
 * 通用方法操作类
 * @author boge.peng
 */
public final class CommonFunctions {
	public static <T> boolean isEmpty(T obj) {
		return obj == null || obj.toString() == null || obj.toString().trim().equals("");
	}
	
	public static <E> boolean isEmpty(List<E> objs) {
		return objs == null || objs.isEmpty();
	}
	
	public static <T> boolean isEmpty(T[] obj) {
		return obj == null || obj.length == 0;
	}

	public static <T> boolean notEmpty(T obj) {
	    return !isEmpty(obj);
    }

    public static <E> boolean notEmpty(List<E> objs) {
        return !isEmpty(objs);
    }

    public static <T> boolean notEmpty(T[] obj) {
        return !isEmpty(obj);
    }
}
