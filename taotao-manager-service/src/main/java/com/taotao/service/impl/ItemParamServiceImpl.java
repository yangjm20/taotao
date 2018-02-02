package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    TbItemParamMapper itemParamMapper;
    @Override
    public EasyUIDataGridResult getEasyUIData(Integer page, Integer rows) {
            //分页处理
            PageHelper.startPage(page, rows);
            //执行查询
            TbItemParamExample example=new TbItemParamExample();
            List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);

            //取分页信息
            PageInfo<TbItemParam> pageInfo=new PageInfo<>(list);
            //返回处理结果
            EasyUIDataGridResult result=new EasyUIDataGridResult();
            result.setTotal(pageInfo.getTotal());
            result.setRows(list);
            return result;

    }

    @Override
    public TaotaoResult getItemParamByCid(Long cid) {
        TbItemParamExample example=new TbItemParamExample();
        TbItemParamExample.Criteria criteria=example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> tbItemParams = itemParamMapper.selectByExample(example);
        if(tbItemParams!=null && tbItemParams.size()>0){
            TbItemParam tbItemParam= tbItemParams.get(0);
            return TaotaoResult.ok(tbItemParam);
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemParam(Long cid, String paramData) {
        TbItemParam itemParam=new TbItemParam();
        Date date=new Date();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        itemParam.setCreated(date);
        itemParam.setUpdated(date);
        itemParamMapper.insert(itemParam);
        return TaotaoResult.ok();
    }
}
