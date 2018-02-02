package com.taotao.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

public class TestPageHelper {

    @Test
    public void testPageHelper() throws Exception{
        //1获取mapper代理对象
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
        //2设置分页
        PageHelper.startPage(1,30);
        //3执行查询
        TbItemExample example=new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        //4取分页后的结果
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        long total = pageInfo.getTotal();
        System.out.println(total);
        int pages = pageInfo.getPages();
        System.out.println(pages);

    }
}
