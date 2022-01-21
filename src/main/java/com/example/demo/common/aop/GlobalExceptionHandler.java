package com.example.demo.common.aop;

import com.example.demo.common.exception.DataException;
import com.example.demo.common.response.Response;
import com.example.demo.common.response.Result;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * @Description: 处理参数不匹配的异常
     * @Param: [request, e]
     * @return: com.example.demo.common.response.Response
     */
    @ExceptionHandler(BindException.class)
    public Response handleBindException(HttpServletRequest request, BindException e) {
        return Response.failure(Result.PARAM_NOT_MATCH);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException e) {
        return Response.failure(Result.PARAM_NOT_MATCH);
    }

    /**
     * @Description: 处理参数效验失败的异常
     * @Param: [request, e]
     * @return: com.example.demo.common.response.Response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentNotValidException(HttpServletRequest request, BindException e) {
        String errorField = e.getFieldError().getField();
        String errorMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        return Response.failure(Result.PARAM_IS_INVALID, new HashMap<String, String>() {
            {
                put(errorField, errorMsg);
            }
        });
    }

    /**
     * @Description: 处理json数据解析失败的异常
     * @Param: [request, e]
     * @return: com.example.demo.common.response.Response
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException e) {
        return Response.failure(Result.PARAM_PARSE_FAILURE);
    }

    /**
     * @Description: 处理参数映射失败的异常
     * @Param: [request, e]
     * @return: com.example.demo.common.response.Response
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Response handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        String errorField = e.getName();
        String errorMsg = e.getCause().getMessage();
        return Response.failure(Result.PARAM_CAST_FAILURE, new HashMap<String, String>() {
            {
                put(errorField, errorMsg);
            }
        });
    }

//    /**
//     * @Description: 处理用户登录状态失效的异常
//     * @Param: [request, e]
//     * @return: com.example.demo.common.response.Response
//     */
//    @ExceptionHandler(UnauthenticatedException.class)
//    public Response handleUnauthenticatedException(HttpServletRequest request, UnauthenticatedException e) {
//        return Response.failure(Result.USER_NOT_LOGIN);
//    }
//
//    /**
//    * @Description: 处理用户无权限的异常
//     * @Param: [request, e]
//    * @return: com.example.demo.common.response.Response
//    */
//    @ExceptionHandler(UnauthorizedException.class)
//    public Response handleUnauthorizedException(HttpServletRequest request, UnauthorizedException e) {
//        return Response.failure(Result.USER_IS_FORBIDDEN);
//    }
//
//    /**
//    * @Description: 处理用户登录失败的异常
//     * @Param: [request, e]
//    * @return: com.example.demo.common.response.Response
//    */
//    @ExceptionHandler(IncorrectCredentialsException.class)
//    public Response handleUnauthenticatedException(HttpServletRequest request, IncorrectCredentialsException e) {
//        return Response.failure(Result.USER_LOGIN_ERROR);
//    }
//
//    @ExceptionHandler(UnknownAccountException.class)
//    public Response handleUnknownAccountException(HttpServletRequest request, UnknownAccountException e) {
//        return Response.failure(Result.USER_LOGIN_ERROR);
//    }

    /**
     * @Description: 处理HTTP谓词不支持的异常
     * @Param: [request, e]
     * @return: com.example.demo.common.response.Response
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        return Response.failure(Result.METHOD_NOT_ALLOWED);
    }

    /**
     * @Description: 处理数据异常
     * @Param: [request, e]
     * @return: com.example.demo.common.response.Response
     */
    @ExceptionHandler(DataException.class)
    public Response handleDataException(HttpServletRequest request, DataException exception) {
        return Response.failure(exception.getResult());
    }

    /**
     * @Description: 处理传参异常
     * @Param: [request, e]
     * @return: com.example.demo.common.response.Response
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Response handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException exception) {
        String msg = exception.getMessage();
        if("Page index must not be less than zero!".equals(msg)) {
            return Response.failure(Result.PARAM_IS_INVALID, new HashMap<String, String>() {
                {
                    put("pageNum", "pageNum不能小于1");
                }
            });
        }

        return Response.failure(Result.PARAM_IS_INVALID);
    }


    /**
     * @Description: 处理其他的异常
     * @Param: [request, e]
     * @return: com.example.demo.common.response.Response
     */
    @ExceptionHandler(Exception.class)
    public Response handleException(HttpServletRequest request, Exception e) {
        System.out.println(request.getRequestURI());
//        System.out.println(e.toString());
        return Response.failure(Result.ERROR);
    }
}
