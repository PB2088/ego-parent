package com.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.domain.EasyUITree;
import com.ego.domain.TbItemCat;
import com.ego.dubbo.service.TbItemCategoryDubboService;
import com.ego.manage.service.TbItemCategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boge.peng
 * @create 2019-03-12 13:42
 */
@Service
public class TbItemCategoryImpl implements TbItemCategoryService {

    @Reference
    private TbItemCategoryDubboService itemCategoryDubboService;

    @Override
    public List<EasyUITree> showItemCategoryByPid(long pid) {
        List<TbItemCat> tbItemCategorys = itemCategoryDubboService.showItemCategoryByPid(pid);

        List<EasyUITree> easyUITrees = new ArrayList<>();
        for (TbItemCat tbItemCategory : tbItemCategorys) {
            EasyUITree tree = new EasyUITree();
            tree.setId(tbItemCategory.getId());
            tree.setText(tbItemCategory.getName());
            tree.setState(tbItemCategory.getIsParent()?"closed":"open");

            easyUITrees.add(tree);
        }

        return easyUITrees;
    }
}
