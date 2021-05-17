package com.sky.week02nio;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TestOkHttp {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = "http://localhost:8081";
        // 请求
        Request request = new Request.Builder().url(url).build();
        // 响应
        Response response = client.newCall(request).execute();
        String text = response.body().string();
        System.out.println(url + ": " + text);
        // 关闭客户端
        client = null;
    }
}
