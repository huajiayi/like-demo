package com.example.demo.common.exception;

import com.example.demo.common.response.Result;

public class DataException extends RuntimeException {
    private Result result;

    public DataException(Result result) {
        super(result.getMsg());
        this.result = result;
    }

    public DataException(Result result, Throwable cause) {
        super(result.getMsg(), cause);
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
