package com.study.spring.starter;

import com.study.spring.ApplicationTest;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

@Slf4j
public class OkHttpStartTest extends ApplicationTest {


    @Autowired
    private OkHttpClient okHttpClient;

    @Test
    public void startTest() throws IOException {
        Request request = new Request.Builder().url("https://baidu.com").build();
        Call call = okHttpClient.newCall(request);
        // 一行一行的读取
//        InputStream inputStream = call.execute().body().byteStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        String line;
//        // 逐行读取直到流结束
//        while ((line = reader.readLine()) != null) {
//            // 处理每一行内容
//            log.info("{}", line);
//        }
        log.info("{}", call.execute().body().string());
    }
}
