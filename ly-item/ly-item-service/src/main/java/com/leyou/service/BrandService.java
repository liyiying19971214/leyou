package com.leyou.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.utils.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.mapper.BrandMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;


    public PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc,String key) {
            //分页
        PageHelper.startPage(page,rows);

            //过滤
        Example example=new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("name","%"+key+"%").orEqualTo("letter",key.toUpperCase());
        }

        //排序
        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause= sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }



        //查询
        List<Brand> brands = brandMapper.selectByExample(example);

        PageInfo<Brand> info=new  PageInfo<Brand>(brands);


        return  new PageResult<Brand>(info.getTotal(),brands);
    }


            @Transactional
            public void saveBrand(Brand brand, List<Long> cids) {
              brandMapper.insert(brand);
              for (Long cid:cids) {
                  brandMapper.insertCategoryBrand(cid,brand.getId());
              }


    }
}
