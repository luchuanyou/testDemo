package com.lcy.common.utils.http;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.util.Map.Entry;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    private static final String UTF_8 = "UTF-8";

    public static String doPostWithJsonAndSign(String url, String json) {

        /*String returnValue = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();

            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);

            //设置超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .build();
            httpPost.setConfig(requestConfig);

            //第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json, "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);

            String sign = ApiInterfaceValid.sign(signKey, json);
            httpPost.setHeader("sign", sign);

            //第四步：发送HttpPost请求，获取返回值
            returnValue = httpClient.execute(httpPost, responseHandler); //调接口获取返回值时，必须用此方法
            //         CloseableHttpResponse httpResonse = httpClient.execute(httpPost);
            //         int statusCode = httpResonse.getStatusLine().getStatusCode();
            //         if(statusCode!=200)
            //         {
            //              System.out.println("请求发送失败，失败的返回参数为："+httpResonse.getStatusLine());
            //              returnValue = httpResonse.getStatusLine().toString();
            //         }
            //

        } catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch(IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //第五步：处理返回值
        return returnValue;*/


        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpPost post = new HttpPost(url);
//        JsonObject jsonObject = null;
        String result = null;
        try {
            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
            //设置超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .build();
            post.setConfig(requestConfig);
            // 修复 POST json 导致中文乱码
            HttpEntity entity = new StringEntity(json, "UTF-8");
            post.setEntity(entity);
            post.setHeader("content-type", "application/json");
//            post.setHeader("sign", sign);
            HttpResponse resp = closeableHttpClient.execute(post);

            InputStream respIs = resp.getEntity().getContent();
            result = respIs.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String doPost(String url, Map<String, String> map) {

        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);

            //设置超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .build();
            httpPost.setConfig(requestConfig);

            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>)iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if(list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, UTF_8);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null) {
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null) {
                    result = EntityUtils.toString(resEntity, UTF_8);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
