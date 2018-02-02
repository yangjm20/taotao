package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatResult(Long parentId) {
        //根据parentId查询类目
        TbItemCatExample example=new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询条件
        List<TbItemCat> lists = tbItemCatMapper.selectByExample(example);
        List<EasyUITreeNode> resultList=new ArrayList<>();

        for(TbItemCat tbItemCat:lists) {
            EasyUITreeNode itemCatResult = new EasyUITreeNode();
            itemCatResult.setId(tbItemCat.getId());
            itemCatResult.setText(tbItemCat.getName());
            itemCatResult.setState(tbItemCat.getIsParent() ? "closed" : "open");
            resultList.add(itemCatResult);
        }
        return resultList;
    }
}
