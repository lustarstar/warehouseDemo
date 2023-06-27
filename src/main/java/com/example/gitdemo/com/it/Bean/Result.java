package com.example.gitdemo.com.it.Bean;


public class Result {
    private Integer code;
    private String message;
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("成功");
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("成功");
        result.setData(data);
        return result;
    }

    // 省略 getter 和 setter 方法
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    // 省略 getter 和 setter 方法
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // 省略 getter 和 setter 方法
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
