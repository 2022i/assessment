package com.back.assessment.dto;

import lombok.Data;

/**
 * @author lzz
 */
@Data
public class Request<T>{
    private int code;
    private String msg;
    private T data;

    public static <T> Request<T> success() {
        Request<T> result = new Request<>();
        result.setCode(200);
        result.setMsg("操作成功");
        return result;
    }

    public static <T> Request<T> successLogin(T data) {
        Request<T> result = new Request<>();
        result.setCode(200);
        result.setMsg("成功登录");
        result.setData(data);
        return result;
    }


    public static <T> Request<T> notFoundUser(T data) {
        Request<T> result = new Request<>();
        result.setCode(404);
        result.setMsg("不存在该用户");
        result.setData(data);
        return result;
    }

    public static <T> Request<T> errorPassword() {
        Request<T> result = new Request<>();
        //401 （未授权） 请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应。
        result.setCode(401);
        result.setMsg("密码错误");
        result.setData(null);
        return result;
    }

    public static <T> Request<T> successRegister(T data) {
        Request<T> result = new Request<>();
        result.setCode(200);
        result.setMsg("成功注册");
        result.setData(data);
        return result;
    }

    public static <T> Request<T> successMail(T data) {
        Request<T> result = new Request<>();
        result.setCode(200);
        result.setMsg("成功发送验证码");
        result.setData(data);
        return result;
    }


    public static <T> Request<T> alreadyExistsEmail(T data) {
        Request<T> result = new Request<>();
        //409 （冲突） 服务器在完成请求时发生冲突。服务器必须在响应中包含有关冲突的信息。
        result.setCode(409);
        result.setMsg("该邮箱已被使用");
        result.setData(data);
        return result;
    }

    public static <T> Request<T> alreadyExistsUsername(T data) {
        Request<T> result = new Request<>();
        //409 （冲突） 服务器在完成请求时发生冲突。服务器必须在响应中包含有关冲突的信息。
        result.setCode(409);
        result.setMsg("该用户名已被使用");
        result.setData(data);
        return result;
    }

    public static <T> Request<T> errorCode() {
        Request<T> result = new Request<>();
        //401 （未授权） 请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应。
        result.setCode(401);
        result.setMsg("验证码错误");
        result.setData(null);
        return result;
    }

    public static <T> Request<T> error(HttpStatus status) {
        Request<T> result = new Request<>();
        result.setCode(status.getCode());
        result.setMsg(status.getMsg());
        return result;
    }
}
