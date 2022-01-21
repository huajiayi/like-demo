package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.Like;
import com.example.demo.dao.Product;
import com.example.demo.mapper.LikeMapper;
import com.example.demo.service.LikeService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16019
 * @description 针对表【like】的数据库操作Service实现
 * @createDate 2022-01-19 17:48:34
 */
//@Service
public class ThirdVersionLikeServiceImpl extends ServiceImpl<LikeMapper, Like>
        implements LikeService{

    @Autowired
    private ProductService productService;


    @Override
    public boolean like(Integer userId, Integer productId) {
        String lock = buildLock(userId, productId);
        synchronized (lock) {
            // 查询是否有记录，如果有记录直接返回
            Like like = getOne(new QueryWrapper<Like>().lambda()
                    .eq(Like::getUserId, userId)
                    .eq(Like::getProductId, productId), false);
            if(like != null) {
                return true;
            }

            // 保存并商品点赞数加1
            save(Like.builder()
                    .userId(userId)
                    .productId(productId)
                    .build());
            return productService.update(new UpdateWrapper<Product>().lambda()
                    .setSql("like_count = like_count + 1")
                    .eq(Product::getId, productId));
        }
    }

    @Override
    public boolean unlike(Integer userId, Integer productId) {
        boolean isSuccess = remove(new QueryWrapper<Like>().lambda()
                .eq(Like::getUserId, userId)
                .eq(Like::getProductId, productId));
        if(isSuccess) {
            return productService.update(new UpdateWrapper<Product>().lambda()
                    .setSql("like_count = like_count - 1")
                    .eq(Product::getId, productId));
        }

        return true;
    }

    @Override
    public Integer getProductLikeCount(Integer productId) {
        Product product = productService.getOne(new QueryWrapper<Product>().lambda()
                .eq(Product::getId, productId), false);
        return product == null ? 0 : product.getLikeCount();
    }

    @Override
    public boolean isLiked(Integer userId, Integer productId) {
        return getOne(new QueryWrapper<Like>().lambda()
                .eq(Like::getUserId, userId)
                .eq(Like::getProductId, productId)) != null;
    }

    @Override
    public List<Integer> getLikeUserIdsOfProduct(Integer productId) {
        return list(
                new QueryWrapper<Like>().lambda()
                        .select(Like::getUserId)
                        .eq(Like::getProductId, productId)
        ).stream()
                .map(Like::getUserId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getLikeProductIdsOfUser(Integer userId) {
        return list(
                new QueryWrapper<Like>().lambda()
                        .select(Like::getProductId)
                        .eq(Like::getUserId, userId)
        ).stream()
                .map(Like::getProductId)
                .collect(Collectors.toList());
    }

    private String buildLock(Integer userId, Integer productId) {
        StringBuilder sb = new StringBuilder();
        sb.append(userId);
        sb.append("::");
        sb.append(productId);
        String lock = sb.toString().intern();

        return lock;
    }
}
