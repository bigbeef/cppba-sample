package com.cppba.util;

import com.google.common.io.Closeables;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public abstract class Images {

    public static final String IMAGE_FORMAT_DEFAULT = "jpg";
    public static final int    TIMEOUT              = 5000;

    public static final String ERROR_IMAGE_GET      = "获取图片错误!";
    public static final String ERROR_IMAGE_DECODE   = "图片解码错误!";
    public static final String ERROR_IMAGE_SAVE     = "保存图片错误!";

    public static BufferedImage getImage(String url, int timeout) throws IOException {
        InputStream ins = null;
        try {
            URL _url_ = new URL(url);
            URLConnection con = _url_.openConnection();
            con.setConnectTimeout(timeout);
            con.setReadTimeout(timeout);
            ins = con.getInputStream();
            return ImageIO.read(ins);
        } catch (IOException ex) {
            throw new IOException(ERROR_IMAGE_GET, ex);
        } finally {
            Closeables.closeQuietly(ins);
        }
    }

    public static BufferedImage getImage(String url) throws IOException {
        return getImage(url, TIMEOUT);
    }

    /**
     * 根据路径获取图片并转换为byte[]
     */
    public static byte[] getByte(String path, String format) throws IOException {
        return getByte(getImage(path), format);
    }

    /**
     * 根据BufferedImage对象获取图片并转换为byte[]
     * 图片的格式默认：jpg
     */
    public static byte[] getByte(BufferedImage image, String format) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();// no need to close
        try {
            ImageIO.write(image, format, bos);
        } catch (IOException ex) {
            throw new IOException(ERROR_IMAGE_GET, ex);
        }
        return bos.toByteArray();
    }

}
