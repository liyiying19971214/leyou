package com.leyou.web;

import com.leyou.item.pojo.Category;
import com.leyou.service.CategoryService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseErrorHandler;

import java.util.List;

@RequestMapping("category")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService CategoryService;


    /**
     *
     * 查询id方法
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>>  queryCategoryListByPid(@RequestParam("pid")Long  pid) throws Exception {

        System.out.println("day");
        return  ResponseEntity.ok(CategoryService.queryCategoryListByPid(pid));
           }

        }


