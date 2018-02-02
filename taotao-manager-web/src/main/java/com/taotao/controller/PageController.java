package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.service.ItemParamService;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page,Integer rows){
        EasyUIDataGridResult easyUIData = itemService.getEasyUIData(page, rows);
        return easyUIData;
    }

    @RequestMapping("/item/param/list")
    @ResponseBody
    public EasyUIDataGridResult getItemParamList(Integer page,Integer rows){
        EasyUIDataGridResult easyUIData = itemParamService.getEasyUIData(page, rows);
        return easyUIData;
    }


}
