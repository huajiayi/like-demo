package com.example.demo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @TableName like
 */
@TableName(value ="`like`")
@Data
@Builder
public class Like implements Serializable {
    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer productId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}