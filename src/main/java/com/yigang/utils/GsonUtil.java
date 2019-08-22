
package com.yigang.utils;

import com.google.gson.Gson;
import java.lang.reflect.Type;

public class GsonUtil {
	
     //把对象通过GSON封装成string便于在http上传输
    public  static <T> String entityToJson(T type){
        Gson gson = new Gson();
        String result = gson.toJson(type);
        return result;
    }

    //把从http上接收的对象通过GSON解析为泛型
    public static <T> T jsonToEntity(String jsonData, Type type){
        return new Gson().fromJson(jsonData,type);
    }
}
