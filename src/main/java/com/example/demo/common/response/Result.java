package com.example.demo.common.response;

public enum Result implements IResult {

    /* 成功状态码 */
    SUCCESS(1, "成功"),
    /* 未知错误状态码 */
    ERROR(2, "未知错误"),
    /* 用户端错误: 1001-1999 */
    PARAM_PARSE_FAILURE(1001, "json数据解析失败"),
    PARAM_IS_INVALID(1002, "参数效验失败"),
    PARAM_NOT_MATCH(1003, "参数不匹配"),
    METHOD_NOT_ALLOWED(1004, "HTTP谓词不支持"),
    PARAM_CAST_FAILURE(1005, "参数映射失败"),
    /* 用户错误: 2001-2999 */
    USER_NOT_LOGIN(2001, "用户登录状态失效"),
    USER_LOGIN_ERROR(2002, "用户名或密码错误"),
    USER_HAS_EXISTED(2003, "用户名已存在"),
    USER_IS_FORBIDDEN(2004, "用户无访问权限"),
    /* 服务器错误 */
    DATA_HAS_EXISTED(3001, "数据已存在"),
    DATA_NOT_FOUND(3002, "数据不存在");

    private int code;

    private String msg;

    Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
