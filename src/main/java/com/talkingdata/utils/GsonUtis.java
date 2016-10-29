package com.talkingdata.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * User��    ysl
 * Date:   2016/10/18
 * Time:   17:49
 */
public class GsonUtis {

    public static void main(String[] args)
    {
        String jsonData = "{\"code\":200,\"status\":\"SUCCEED\",\"message\":\"\"," +
                "\"time\":\"2016-10-18 17:43:16\",\"trace\":\"\",\"data\":" +
                "{\"token\":\"7HWHYGkkvPBzk3SZpwdnBsiVLniyG5xk\",\"validTime\":1140000}}";
        Map<String ,Object> responseMap = parseData(jsonData);
        System.out.println(responseMap);
         Map<String,Object> dataMap = (Map<String,Object>)responseMap.get("data");
        System.out.println(dataMap.get("token"));


    }


    /**
     *将json的数据转换为map
     * @param jsonData  :json�ַ�������
     * @return
     */
    public  static Map<String, Object> parseData(String jsonData)
    {
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        Map<String, Object> map = g.fromJson(jsonData, new TypeToken<Map<String, Object>>() {}.getType());
        return map;
    }
}
