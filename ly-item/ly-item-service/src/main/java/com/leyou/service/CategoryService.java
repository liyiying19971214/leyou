package com.leyou.service;

import com.leyou.item.pojo.Category;
import com.leyou.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper   CategoryMapper;


    public List<Category> queryCategoryListByPid(Long pid) throws Exception {
    Category category=new Category();
        category.setParentId(pid);
        List<Category> list = CategoryMapper.select(category);
        if(CollectionUtils.isEmpty(list)){
            throw new Exception("没找商品");
        }

        return  list;
    }
}
