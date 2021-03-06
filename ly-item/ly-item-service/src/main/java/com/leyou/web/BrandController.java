package com.leyou.web;

import com.leyou.common.utils.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;



    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key) {
            PageResult<Brand> result   =  brandService.queryBrandByPageAndSort(page,rows,sortBy,desc,key);
        return ResponseEntity.ok(result);
    }


    /**
     * 新增的方法
     * @param brand
     * @param cids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand , @RequestParam("cids")  List<Long>  cids){
                  brandService.saveBrand(brand,cids);

        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
