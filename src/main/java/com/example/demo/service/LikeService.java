package com.example.demo.service;

import com.example.demo.dao.Like;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 16019
* @description 针对表【like】的数据库操作Service
* @createDate 2022-01-19 17:48:34
*/
public interface LikeService extends IService<Like> {

    boolean like(Integer userId, Integer productId);

    boolean unlike(Integer userId, Integer productId);

    Integer getProductLikeCount(Integer productId);

    boolean isLiked(Integer userId, Integer productId);

    List<Integer> getLikeUserIdsOfProduct(Integer productId);

    List<Integer> getLikeProductIdsOfUser(Integer userId);
}
