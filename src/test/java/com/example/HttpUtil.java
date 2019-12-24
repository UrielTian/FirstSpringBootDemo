package com.example;

import com.alibaba.fastjson.JSONObject;
import com.example.firstspringbootdemo.controller.HelloWorldController;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;

/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package  
 * FileName: HttpUtil.java
 * Author:   John
 * Date:     2016年11月10日 上午11:04:49
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   John           2016年11月10日上午11:04:49                     1.0                  
 *===============================================================================================
 */

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年11月10日 <br>
 * 作者：John <br>
 * 说明：<br>
 */
public class HttpUtil {

    Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    HttpURLConnection httpURLConnection = null;
    
    public void connect(String urlStr) throws IOException{
        URL url = new URL(urlStr);
        logger.info("开始初始化服务器连接...");
        logger.debug("服务器地址：[ " + url + " ]");
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-type",
                new StringBuilder().append("application/json;charset=utf-8").toString());
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.connect();
        logger.info("请求方式[ http,POST ]");
        logger.info("服务器连接完成...");
    }
    
    
    public void request(String message) throws Exception{
        logger.info("上送服务报文开始...");
        logger.error("上送报文:[" + message + "]");
        OutputStream outputStream = null;
        try {
            outputStream = httpURLConnection.getOutputStream();
            // 注意编码格式，防止中文乱码
            if(null==(message)){
                outputStream.write(message.getBytes("utf-8"));
            }else{
                outputStream.write("".getBytes());
            }
            
            if (null != outputStream)
                outputStream.close();
            logger.info("上送服务报文完成...");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != outputStream)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    
    
    public String response() throws IOException{
        logger.debug("接收服务器响应报文开始...");
        InputStream in = null;
        StringBuilder sb = new StringBuilder(1024);
        BufferedReader br = null;
        String temp = null;
        int code = httpURLConnection.getResponseCode();
        if (200 == code) {
            in = httpURLConnection.getInputStream();
            br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            while (null != (temp = br.readLine())) {
                sb.append(temp);
            }
        } else {
            in = httpURLConnection.getErrorStream();
            br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            while (null != (temp = br.readLine())) {
                sb.append(temp);

            }
        }
        String str1 = sb.toString();
        logger.error("报文： [ " + str1 + " ] ");
        logger.info("接收服务器响应报文完成");
        return str1;
    }


    @Test
    public void test() throws Exception {
//        String url ="http://localhost:8083/hello";

        String url ="https://onlinepay.cupdata.com/weixin/wxRights/getShareInf.do";
        String msg = assJson();
        connect(url);
        request(msg);
        System.out.println("resp:" + response());
    }

    private String assJson() {
        JSONObject obj = new JSONObject();
        obj.put("bankNum","6586");
        obj.put("url","");

        return obj.toString();
    }

}
