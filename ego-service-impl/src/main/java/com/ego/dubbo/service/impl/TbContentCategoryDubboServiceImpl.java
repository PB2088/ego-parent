package com.ego.dubbo.service.impl;

import com.ego.commons.utils.CommonFunctions;
import com.ego.domain.TbContentCategory;
import com.ego.domain.TbContentCategoryExample;
import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.mapper.TbContentCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author boge.peng
 * @create 2019-03-13 22:11
 */
public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<TbContentCategory> selectByPid(long pid) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(pid).andStatusEqualTo(1);

        return contentCategoryMapper.selectByExample(example);
    }

    @Override
    public int insertContentCategory(TbContentCategory contentCategory) {
        int index = contentCategoryMapper.insertSelective(contentCategory);

        TbContentCategory parent = new TbContentCategory();
        parent.setId(contentCategory.getParentId());
        parent.setIsParent(true);

        index += updateContentCategoryById(parent);

        return index;
    }

    @Override
    public int updateContentCategoryById(TbContentCategory contentCategory) {
        return contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
    }

    @Override
    public TbContentCategory selectById(long id) {
        return contentCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteContentCategoryById(TbContentCategory contentCategory) {
        int index = updateContentCategoryById(contentCategory);

        if (index > 0) {
            TbContentCategory category = selectById(contentCategory.getId());

            List<TbContentCategory> tbContentCategories = selectByPid(category.getParentId());
            if (CommonFunctions.isEmpty(tbContentCategories)) {
                TbContentCategory parent = new TbContentCategory();
                parent.setId(category.getParentId());
                parent.setIsParent(false);

                index += updateContentCategoryById(parent);
            }
        }

        return index;
    }
}
