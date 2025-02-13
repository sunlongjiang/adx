package com.hungrystudio.adx.test;

import java.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class MyAppTest {
    public static void main(String[] args) throws IOException {
        String test = "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";

        // Base64解码
        byte[] decodedBytes = Base64.getDecoder().decode(test);

        // 使用GZIP解压
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodedBytes);
             GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            // 使用缓冲区读取解压数据
            byte[] buffer = new byte[1024];
            int length;
            while ((length = gzipInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }

            // 获取解压后的字节数据
            byte[] decompressedData = byteArrayOutputStream.toByteArray();

            // 打印解压后的内容
            System.out.println(new String(decompressedData));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

