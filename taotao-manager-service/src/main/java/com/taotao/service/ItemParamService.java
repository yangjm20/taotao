package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

public interface ItemParamService {
    EasyUIDataGridResult getEasyUIData(Integer page, Integer rows);
    TaotaoResult getItemParamByCid(Long cid);
    TaotaoResult insertItemParam(Long cid,String paramData);
}
