package com.example.demo;

import com.example.demo.dao.Product;
import com.example.demo.dao.User;
import com.example.demo.service.LikeService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private LikeService likeService;

    @Test
    void contextLoads() {
    }

    @Test
    void createUser() {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            User user = User.builder()
                    .id(i)
                    .name(String.valueOf(i))
                    .build();
            users.add(user);
        }
        userService.saveBatch(users);
    }


    @Test
    void createProject() {
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Product product = Product.builder()
                    .id(i)
                    .name(String.valueOf(i))
                    .build();
            products.add(product);
        }
        productService.saveBatch(products);
    }

    @Test
    void testLike() {
//        int count = likeService.getProductLikeCount(2);
//        System.out.println(String.format("商品 2 被点赞 %d 次", count));
//
//        IntStream.range(1, 100).forEach((i) -> {
//            likeService.like(1,1);
//        });
//        boolean isLiked = likeService.isLiked(1, 1);
//        System.out.println(String.format("用户 1 %s点赞商品 1 ", isLiked ? "成功" : "没有"));
//        count = likeService.getProductLikeCount(1);
//        System.out.println(String.format("商品 1 被点赞 %d 次", count));

//        IntStream.range(1, 100).forEach((i) -> {
//            likeService.unlike(1,2);
//        });
//        int count = likeService.getProductLikeCount(2);
//        System.out.println(String.format("商品 2 被点赞 %d 次", count));

        String a = "1" + "::" + "1";
        String b = "1" + "::" + "1";
        System.out.println(a == b);
    }
}
