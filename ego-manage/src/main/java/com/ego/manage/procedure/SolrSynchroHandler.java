package com.ego.manage.procedure;

import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.JsonUtils;

import java.util.Map;

/**
 * 线程-solr后台数据同步
 * @author boge.peng
 * @create 2019-03-16 14:09
 */
public class SolrSynchroHandler implements Runnable {

    private String url;
    private Map<String,Object> param;

    private SolrSynchroHandler(String url,Map<String,Object> param) {
        this.url = url;
        this.param = param;
    }

    public static SolrSynchroHandler builder(String url,Map<String,Object> param) {
        return new SolrSynchroHandler(url,param);
    }

    @Override
    public void run() {
        HttpClientUtil.doPostJson(url, JsonUtils.objectToJson(param));
    }
}
