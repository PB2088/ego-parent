package com.ego.search.service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.Map;

/**
 * @author boge.peng
 * @create 2019-03-16 0:32
 */
public interface TbItemSearchService {
    /**
     * 初始化Solr数据
     */
    void initSolr() throws IOException, SolrServerException;

    /**
     * 商品搜索
     * @param query
     * @param page
     * @param rows
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    Map<String,Object> selectByQuery(String query,int page,int rows) throws IOException, SolrServerException;

    /**
     * 新增商品
     * @param map
     * @return
     */
    int add(Map<String,Object> map,String desc) throws IOException, SolrServerException;
}
