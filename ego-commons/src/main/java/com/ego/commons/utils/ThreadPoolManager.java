package com.ego.commons.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池管理(线程统一调度管理)
 */
public final class ThreadPoolManager {

    private ThreadPoolManager() {
    }

    private static class ThreadPoolHolder {
        private static final ExecutorService INSTANCE = Executors.newFixedThreadPool(30);
    }

    public static ExecutorService getInstance() {
        return ThreadPoolHolder.INSTANCE;
    }
}
