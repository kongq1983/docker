package com.kq.product.controller;

import com.kq.docker.dto.DtoResult;
import com.kq.docker.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value="/product")
public class ProductController {

    private List<ProductDto> list = new ArrayList<>();

    @RequestMapping(value="/add")
    public DtoResult add(ProductDto e) {

        log.debug("添加产品接收参数={}",e);

        addToList(e);

        DtoResult result = new DtoResult();
        result.setResult(e);
        return result;
    }

    @RequestMapping(value="/list")
    public DtoResult list() {

        DtoResult result = new DtoResult();
        result.setResult(list);

        return result;
    }


    private void addToList(ProductDto e) {

        if(list.size()>100) {
            list.remove(0);
        }

        list.add(e);

    }


}
