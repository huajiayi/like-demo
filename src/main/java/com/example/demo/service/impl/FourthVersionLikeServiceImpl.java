package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.util.RedisUtil;
import com.example.demo.dao.Like;
import com.example.demo.dao.Product;
import com.example.demo.mapper.LikeMapper;
import com.example.demo.service.LikeService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16019
 * @description 针对表【like】的数据库操作Service实现
 * @createDate 2022-01-19 17:48:34
 */
@Service
public class FourthVersionLikeServiceImpl extends ServiceImpl<LikeMapper, Like>
        implements LikeService{

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean like(Integer userId, Integer productId) {
        List<String> keys = new ArrayList<>();
        keys.add(buildUserRedisKey(userId));
        keys.add(buildProductRedisKey(productId));

        int value1 = 1;

        redisUtil.execute("lua-script/like.lua", keys, value1);

        return true;
    }

    @Override
    public boolean unlike(Integer userId, Integer productId) {
        List<String> keys = new ArrayList<>();
        keys.add(buildUserRedisKey(userId));
        keys.add(buildProductRedisKey(productId));

        int value1 = 0;

        redisUtil.execute("lua-script/like.lua", keys, value1);

        return true;
    }

    @Override
    public Integer getProductLikeCount(Integer productId) {
        String key = buildProductRedisKey(productId);
        Integer count = (Integer) redisUtil.get(key);

        return count == null ? 0 : count;
    }

    @Override
    public boolean isLiked(Integer userId, Integer productId) {
        String userKey = buildUserRedisKey(userId);
        String productKey = buildProductRedisKey(productId);

        return redisUtil.sHasKey(userKey, productKey);
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

    private String buildKey(Integer userId, Integer productId) {
        StringBuilder sb = new StringBuilder();
        sb.append(userId);
        sb.append("::");
        sb.append(productId);
        String lock = sb.toString();

        return lock;
    }

    private String buildUserRedisKey(Integer userId) {
        return "userId_" + userId;
    }

    private String buildProductRedisKey(Integer productId) {
        return "productId_" + productId;
    }
}
