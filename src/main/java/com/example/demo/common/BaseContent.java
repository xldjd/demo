package com.example.demo.common;

/**
 * 封装工具类，获取当前用户id
 */

public class BaseContent {

private static ThreadLocal<Long> threadLocal=new ThreadLocal();

public static void setcurrentid(Long id){
threadLocal.set(id);

}

public static Long getcurrentid(){
    return threadLocal.get();
}

}
