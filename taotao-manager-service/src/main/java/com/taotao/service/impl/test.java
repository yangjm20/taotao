package com.taotao.service.impl;


import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class test {

    @Autowired
    private TbItemMapper itemMapper;

    @Test
    public void getTest(){
        long id=(long)(56723);
        TbItemExample example=new TbItemExample();

        TbItemExample.Criteria criteria=example.createCriteria();
        criteria.andIdEqualTo(id);
        List<TbItem> list = itemMapper.selectByExample(example);

        TbItem item=null;
        if(list!=null&&list.size()>0){
            item=list.get(0);
        }
        System.out.println(item);
    }

}
