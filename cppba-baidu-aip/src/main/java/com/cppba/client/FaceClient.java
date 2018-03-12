package com.cppba.client;

import com.baidu.aip.face.AipFace;
import com.cppba.response.FaceMatchResponse;
import com.cppba.util.Images;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author winfed
 * @created 2018/3/9 14:39
 */
public class FaceClient {

    //设置APPID/AK/SK
    public static final String APP_ID = "10899261";
    public static final String API_KEY = "GbGeS8XPIwkvbFBqYHZlwWDl";
    public static final String SECRET_KEY = "FrzNTkgDst7fCWD5XGhdgI3a8Xqfm8XY";

    private FaceClient() {
    }

    private static class AipFaceBuilder {
        private static final AipFace aipFace = new AipFace(APP_ID, API_KEY, SECRET_KEY);
    }

    private static AipFace getAipFace() {
        return AipFaceBuilder.aipFace;
    }

    public static void main(String[] args) {
        // 身份证照片
        String imageUrl2 = "http://ww1.sinaimg.cn/large/9b60a8e5gy1fpa3gr75zuj2074083wh7.jpg";
        // 现场照片
        String imageUrl1 = "http://data.cqxpxt.com/edu/face/reg/5040042601/500381199211218653/20170622094240627.jpg";

        AipFace aipFace = FaceClient.getAipFace();
        HashMap<String, String> options = new HashMap<>();
        // 请求对比的两张图片的类型，示例：“7，13”
        // 7表示生活照：通常为手机、相机拍摄的人像图片、或从网络获取的人像图片等
        // 11表示身份证芯片照：二代身份证内置芯片中的人像照片
        // 12表示带水印证件照：一般为带水印的小图，如公安网小图
        // 13表示证件照片：如拍摄的身份证、工卡、护照、学生证等证件图片，注：需要确保人脸部分不可太小，通常为100px*100px
        options.put("types", "7,13");
        byte[][] imageBytes = new byte[2][];
        try {
            String format = imageUrl1.substring(imageUrl1.lastIndexOf(".") + 1);
            imageBytes[0] = Images.getByte(imageUrl1, format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String format = imageUrl2.substring(imageUrl2.lastIndexOf(".") + 1);
            imageBytes[1] = Images.getByte(imageUrl2, format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject match = aipFace.match(imageBytes, options);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            FaceMatchResponse faceMatchResponse = objectMapper.readValue(match.toString(), FaceMatchResponse.class);
            System.out.println(faceMatchResponse.toString());
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
    }


}
