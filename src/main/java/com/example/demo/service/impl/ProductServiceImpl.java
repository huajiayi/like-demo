package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.Product;
import com.example.demo.service.ProductService;
import com.example.demo.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
* @author 16019
* @description 针对表【product】的数据库操作Service实现
* @createDate 2022-01-20 14:41:48
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

}




