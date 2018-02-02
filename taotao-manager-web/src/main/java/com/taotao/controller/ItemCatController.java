package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "")
public class ItemCatController{
    @Autowired
    private ItemCatService itemCatService;


    @RequestMapping( value ="/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(value = "id" ,defaultValue = "0")Long parentId) {

        System.out.println(parentId);
        List<EasyUITreeNode> list = itemCatService.getItemCatResult(parentId);
        return  list;
    }


   /* public List<EasyUITreeNode> getItemCatList(HttpServletRequest request) {

        String id = request.getParameter("id");

        System.out.println(id);
        Long parentId;
        if(id==null){
           parentId=Long.parseLong("0");
        }else{
            parentId=Long.parseLong(id);
        }

        List<EasyUITreeNode> list = itemCatService.getItemCatResult(parentId);
        return list;
    }*/

}
