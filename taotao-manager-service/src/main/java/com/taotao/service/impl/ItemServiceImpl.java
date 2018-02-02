package com.taotao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Override
    public TbItem getItemById(Long itemId) {

        TbItemExample example=new TbItemExample();

        TbItemExample.Criteria criteria=example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);

       TbItem item=null;
        if(list!=null&&list.size()>0){
            item=list.get(0);
        }
        return item;
    }

    @Override
    public EasyUIDataGridResult getEasyUIData(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        //执行查询
        TbItemExample example=new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        //取分页信息
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        //返回处理结果
        EasyUIDataGridResult result=new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem item, String desc) {
        //设置商品id
        long itemID = IDUtils.genItemId();
        item.setId(itemID);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte)(1));
        Date date=new Date();
        item.setCreated(date);
        item.setUpdated(date);

        itemMapper.insert(item);
        TbItemDesc itemDesc=new TbItemDesc();
        itemDesc.setItemId(itemID);
        itemDesc.setCreated(date);
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(date);
        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }
}
