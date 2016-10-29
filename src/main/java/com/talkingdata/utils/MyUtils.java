package com.talkingdata.utils;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by zoltar on 16-8-14.
 */
public class MyUtils {


    private static final Logger logger = LoggerFactory.getLogger(MyUtils.class);


    /**
     * 通过访问相应的url，获取对应的返回数据
     * @param url
     * @return
     * @throws Exception
     */
    public  static String doHttpGet(String url)throws Exception
    {
            String response_json = null;
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(url);
            getRequest.addHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(getRequest);
             logger.info("response:"+response);
            if (response.getStatusLine().getStatusCode() == 200)
            {
                response_json = IOUtils.toString(response.getEntity().getContent());
            }
            httpClient.getConnectionManager().shutdown();
        return response_json;
    }
}
