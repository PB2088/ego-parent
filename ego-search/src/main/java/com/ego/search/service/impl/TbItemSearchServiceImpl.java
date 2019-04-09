package com.ego.search.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.domain.TbItemExtend;
import com.ego.domain.TbItem;
import com.ego.domain.TbItemCat;
import com.ego.domain.TbItemDesc;
import com.ego.dubbo.service.TbItemCategoryDubboService;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.search.service.TbItemSearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author boge.peng
 * @create 2019-03-16 0:32
 */
@Service
public class TbItemSearchServiceImpl implements TbItemSearchService {
    @Reference
    private TbItemDubboService tbItemDubboService;

    @Reference
    private TbItemCategoryDubboService tbItemCategoryDubboService;

    @Reference
    private TbItemDescDubboService tbItemDescDubboService;

    @Autowired
    private SolrClient solrClient;

    @Override
    public void initSolr() throws IOException, SolrServerException {
        List<TbItem> items = tbItemDubboService.selectAllByStatus(Byte.valueOf("1"));
        for (TbItem item : items) {
            TbItemCat itemCat = tbItemCategoryDubboService.selectById(item.getCid());
            TbItemDesc itemDesc = tbItemDescDubboService.selectByItemId(item.getId());

            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", item.getId());
            doc.addField("item_title", item.getTitle());
            doc.addField("item_sell_point", item.getSellPoint());
            doc.addField("item_price",item.getPrice() );
            doc.addField("item_image", item.getImage());
            doc.addField("item_category_name", itemCat.getName());
            doc.addField("item_desc", itemDesc.getItemDesc());

            solrClient.add(doc);
        }

        solrClient.commit();
    }

    @Override
    public Map<String, Object> selectByQuery(String query, int page, int rows) throws IOException, SolrServerException {
        SolrQuery params = new SolrQuery();
        //设置分页条件
        params.setStart(rows*(page-1));
        params.setRows(rows);

        //设置查询条件
        params.setQuery("item_keywords:"+query);

        //设置高亮
        params.setHighlight(true);
        params.addHighlightField("item_title");
        params.setHighlightSimplePre("<span style='color:red'>");
        params.setHighlightSimplePost("</span>");

        QueryResponse response = solrClient.query(params);
        //未高亮内容
        SolrDocumentList listSolr = response.getResults();
        //高亮内容
        Map<String, Map<String, List<String>>> hh = response.getHighlighting();

        List<TbItemExtend> itemExtends = new ArrayList<>();
        for (SolrDocument document : listSolr) {
            TbItemExtend itemExtend = new TbItemExtend();
            itemExtend.setId(Long.valueOf(document.getFieldValue("id").toString()));

            List<String> list = hh.get(document.getFieldValue("id")).get("item_title");
            if(list!=null&&list.size()>0){
                itemExtend.setTitle(list.get(0));
            }else{
                itemExtend.setTitle(document.getFieldValue("item_title").toString());
            }

            itemExtend.setPrice(Long.valueOf(document.getFieldValue("item_price").toString()));

            Object image = document.getFieldValue("item_image");
            itemExtend.setImages(image==null||image.equals("")?new String[1]:image.toString().split(","));
            itemExtend.setSellPoint(document.getFieldValue("item_sell_point").toString());

            itemExtends.add(itemExtend);
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("itemList", itemExtends);
        resultMap.put("totalPages", listSolr.getNumFound()%rows==0?listSolr.getNumFound()/rows:listSolr.getNumFound()/rows+1);

        return resultMap;
    }

    @Override
    public int add(Map<String, Object> map,String desc) throws IOException, SolrServerException {

        SolrInputDocument doc = new SolrInputDocument();
        doc.setField("id", map.get("id"));
        doc.setField("item_title", map.get("title"));
        doc.setField("item_sell_point", map.get("sellPoint"));
        doc.setField("item_price", map.get("price"));
        doc.setField("item_image", map.get("image"));
        doc.setField("item_category_name", tbItemCategoryDubboService.selectById((Integer)map.get("cid")).getName());
        doc.setField("item_desc", desc);

        UpdateResponse response = solrClient.add(doc);
        solrClient.commit();

        if(response.getStatus()==0){
            return 1;
        }
        return 0;
    }
}
