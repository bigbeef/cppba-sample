package com.cppba.client;

import com.baidu.aip.face.AipFace;
import com.cppba.util.Images;
import org.json.JSONObject;

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
        String imageUrl1 = "http://data.cqxpxt.com/edu/face/reg/5040042601/500381199211218653/20170622094240627.jpg";
        String imageUrl2 = "http://data.cqxpxt.com/edu/face/reg/5040042601/500381199211218653/20170622094240627.jpg";

        AipFace aipFace = FaceClient.getAipFace();
        HashMap<String, String> options = new HashMap<>();
        byte[][] imageBytes = new byte[2][];
        try {
            String format = imageUrl1.substring(imageUrl1.lastIndexOf(".") + 1);
            imageBytes[0] = Images.getByte(imageUrl1, format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String format = imageUrl2.substring(imageUrl1.lastIndexOf(".") + 1);
            imageBytes[1] = Images.getByte(imageUrl2, format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject match = aipFace.match(imageBytes, options);
        System.out.println(match.toString());
    }
}
