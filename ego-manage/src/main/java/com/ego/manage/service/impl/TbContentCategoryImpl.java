package com.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.domain.EasyUITree;
import com.ego.commons.domain.EgoResult;
import com.ego.commons.utils.IDUtils;
import com.ego.domain.TbContentCategory;
import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.manage.service.TbContentCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author boge.peng
 * @create 2019-03-13 22:23
 */
@Service
public class TbContentCategoryImpl implements TbContentCategoryService {

    @Reference
    private TbContentCategoryDubboService contentCategoryDubboService;

    @Override
    public List<EasyUITree> showContentCategory(long id) {
        List<TbContentCategory> contentCategories = contentCategoryDubboService.selectByPid(id);

        List<EasyUITree> trees = new ArrayList<>();
        for (TbContentCategory contentCategory : contentCategories) {
            EasyUITree tree = new EasyUITree();
            tree.setId(contentCategory.getId());
            tree.setText(contentCategory.getName());
            tree.setState(contentCategory.getIsParent()?"closed":"open");

            trees.add(tree);
        }

        return trees;
    }

    @Override
    public EgoResult create(TbContentCategory contentCategory) {

        //1.判断当前新增节点是否为已存在节点,存不进行创建
        List<TbContentCategory> tbContentCategories = contentCategoryDubboService.selectByPid(contentCategory.getParentId());
        for (TbContentCategory tbContentCategory : tbContentCategories) {
            if (Objects.equals(tbContentCategory.getName(),contentCategory.getName())) {
                return EgoResult.FAILED.setData("该分类名已存在");
            }
        }

        //2.新增节点
        long id = IDUtils.genItemId();
        Date currentDate = new Date();

        contentCategory.setId(id);
        contentCategory.setCreated(currentDate);
        contentCategory.setUpdated(currentDate);
        contentCategory.setStatus(1);
        contentCategory.setSortOrder(1);
        contentCategory.setIsParent(false);

        int index = contentCategoryDubboService.insertContentCategory(contentCategory);

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        return EgoResult.SUCCESS.setData(map);
    }

    @Override
    public EgoResult update(TbContentCategory contentCategory) {
        TbContentCategory category = contentCategoryDubboService.selectById(contentCategory.getId());

        List<TbContentCategory> childContentCategories = contentCategoryDubboService.selectByPid(category.getParentId());
        for (TbContentCategory childContentCategory : childContentCategories) {
            if(Objects.equals(childContentCategory.getName(),contentCategory.getName())) {
                return EgoResult.FAILED.setData("该分类名已存在");
            }
        }

        int index = contentCategoryDubboService.updateContentCategoryById(contentCategory);
        if (index > 0) {
            return EgoResult.SUCCESS;
        }

        return EgoResult.FAILED;
    }

    @Override
    public EgoResult delete(TbContentCategory contentCategory) {
        contentCategory.setStatus(2);

        int index = contentCategoryDubboService.deleteContentCategoryById(contentCategory);
        if (index > 0) {
            return EgoResult.SUCCESS;
        }

        return EgoResult.FAILED;
    }
}
