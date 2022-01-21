package com.example.demo.controller;

import com.example.demo.dto.LikeDto;
import com.example.demo.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("like")
    public boolean like(@RequestBody LikeDto likeDto) {
        return likeService.like(likeDto.userId, likeDto.getProductId());
    }

    @PostMapping("unlike")
    public boolean unlike(@RequestBody LikeDto likeDto) {
        return likeService.unlike(likeDto.userId, likeDto.getProductId());
    }

    @GetMapping("getProductLikeCount")
    public Integer getProductLikeCount(@RequestParam Integer productId) {
        return likeService.getProductLikeCount(productId);
    }

    @GetMapping("isLike")
    public boolean isLiked(@RequestBody LikeDto likeDto) {
        return likeService.isLiked(likeDto.getUserId(), likeDto.getProductId());
    }
}
