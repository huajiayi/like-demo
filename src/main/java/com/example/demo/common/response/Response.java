package com.example.demo.common.response;

import java.io.Serializable;

public class Response<T> implements Serializable {

    public int code;

    public String msg;

    public T data;

    public Response(Result result, T data) {
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.data = data;
    }

    /**
    * @Description: 返回不带参数的成功响应
    * @Param: []
    * @return: com.example.demo.common.response.Response
    */
    public static Response success() {
        return new Response(Result.SUCCESS, null);
    }

    /** 
    * @Description: 返回带参数的成功响应
     * @Param: [data] 
    * @return: com.example.demo.common.response.Response<T> 
    */ 
    public static <T> Response<T> success(T data) {
        return new Response(Result.SUCCESS, data);
    }

    /** 
    * @Description: 返回不带参数的错误响应 
    * @Param: [result] 
    * @return: com.example.demo.common.response.Response 
    */ 
    public static Response failure(Result result) {
        return new Response(result, null);
    }

    /** 
    * @Description: 返回带参数的错误响应
     * @Param: [result, data]
    * @return: com.example.demo.common.response.Response<T> 
    */ 
    public static <T> Response<T> failure(Result result, T data) {
        return new Response(result, data);
    }
}
