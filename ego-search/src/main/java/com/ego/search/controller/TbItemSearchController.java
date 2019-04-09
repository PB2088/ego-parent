package com.ego.search.controller;

import com.ego.commons.constant.RequestMappingConstant;
import com.ego.search.service.TbItemSearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 商品搜索控制器
 *
 * @author boge.peng
 * @create 2019-03-16 10:20
 */
@Controller
public class TbItemSearchController {

    @Autowired
    private TbItemSearchService itemSearchService;

    /**
     * 初始化Solr数据
     */
    @RequestMapping(value = RequestMappingConstant.Search.SOLR_INIT, produces = "text/html;charset=utf-8")
    @ResponseBody
    String initSolr() {
        try {
            long starTime = System.currentTimeMillis();
            itemSearchService.initSolr();
            long endTime = System.currentTimeMillis();

            return "初始化完成! 耗时:" + (endTime - starTime) / 1000 + "秒";
        } catch (Exception e) {
            e.printStackTrace();
            return "初始化solr数据失败" + e.getMessage();
        }
    }

    /**
     * 商品搜索
     * @param model
     * @param q
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(RequestMappingConstant.Search.SEARCH)
    public String search(Map<String, Object> model, String q, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "12") int rows) {
        try {
            q = new String(q.getBytes("iso-8859-1"), "utf-8");
            Map<String, Object> resut = itemSearchService.selectByQuery(q, page, rows);

            model.put("query", q);
            model.put("itemList", resut.get("itemList"));
            model.put("totalPages", resut.get("totalPages"));
            model.put("page", page);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "search";
    }

    @RequestMapping(RequestMappingConstant.Search.SOLR_ADD)
    public int add(@RequestBody Map<String,Object> map) {
        try {
            return itemSearchService.add((LinkedHashMap)map.get("item"),map.get("desc").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
