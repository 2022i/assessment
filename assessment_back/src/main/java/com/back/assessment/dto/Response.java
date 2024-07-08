package com.back.assessment.dto;

import com.back.assessment.entity.Project;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzz
 */
@Data
public class Response<T>{
    private int code;
    private String msg;
    private T data;

    public static <T> Response<T> success() {
        Response<T> result = new Response<>();
        result.setCode(200);
        result.setMsg("操作成功");
        return result;
    }

    public static <T> Response<T> successLogin(T data) {
        Response<T> result = new Response<>();
        result.setCode(200);
        result.setMsg("成功登录");
        result.setData(data);
        return result;
    }

    public static <T> Response<T> successSignOut() {
        Response<T> result = new Response<>();
        result.setCode(200);
        result.setMsg("成功退出登录");
        return result;
    }

    public static <T> Response<T> notFoundUser(T data) {
        Response<T> result = new Response<>();
        result.setCode(404);
        result.setMsg("不存在该用户");
        result.setData(data);
        return result;
    }

    public static <T> Response<T> errorPassword() {
        Response<T> result = new Response<>();
        //401 （未授权） 请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应。
        result.setCode(401);
        result.setMsg("密码错误");
        result.setData(null);
        return result;
    }

    public static <T> Response<T> successRegister(T data) {
        Response<T> result = new Response<>();
        result.setCode(200);
        result.setMsg("成功注册");
        result.setData(data);
        return result;
    }

    public static <T> Response<T> successMail(T data) {
        Response<T> result = new Response<>();
        result.setCode(200);
        result.setMsg("成功发送验证码");
        result.setData(data);
        return result;
    }


    public static <T> Response<T> alreadyExistsEmail(T data) {
        Response<T> result = new Response<>();
        //409 （冲突） 服务器在完成请求时发生冲突。服务器必须在响应中包含有关冲突的信息。
        result.setCode(409);
        result.setMsg("该邮箱已被使用");
        result.setData(data);
        return result;
    }

    public static <T> Response<T> alreadyExistsUsername(T data) {
        Response<T> result = new Response<>();
        //409 （冲突） 服务器在完成请求时发生冲突。服务器必须在响应中包含有关冲突的信息。
        result.setCode(409);
        result.setMsg("该用户名已被使用");
        result.setData(data);
        return result;
    }

    public static <T> Response<T> errorCode() {
        Response<T> result = new Response<>();
        //401 （未授权） 请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应。
        result.setCode(401);
        result.setMsg("验证码错误");
        result.setData(null);
        return result;
    }

    public static <T> Response<T> error(HttpStatus status) {
        Response<T> result = new Response<>();
        result.setCode(status.getCode());
        result.setMsg(status.getMsg());
        return result;
    }


    public static List<Project> notFoundProject() {
        Response<List<Project>> result = new Response<>();
        result.setCode(404);
        result.setMsg("没有找到项目");
        return new ArrayList<>();
    }

//    public static List<Project> successProject(List<Project> projectList) {
//        Response<List<Project>> result = success();
//        result.setCode(200);
//        return projectList;
//    }
    public static Project notFoundProjectId(int id){
        Project result = new Project();
        result.setId(id);
        result.setName("没有找到项目");
        return result;
    }

    public static <T> Response<T> codeExpired() {
        Response<T> result = new Response<>();
        result.setCode(404);
        result.setMsg("验证码已过期");
        return result;
    }
}
