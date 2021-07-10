package com.cppba.selenium;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.http.HttpUtil;
import lombok.SneakyThrows;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明：selenium
 *
 * @author winfed
 **/
public class Selenium {

    /**
     * https://blog.csdn.net/qq_22003641/article/details/79137327
     * chrome驱动:https://sites.google.com/a/chromium.org/chromedriver/home
     */
    @SneakyThrows
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:/opt/chromedriver91/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.zq12369.com/environment.php?city=%E9%87%8D%E5%BA%86&tab=city");
            Map<String, String> pointData = getPointDayData(driver, DateUtil.parse("2021-07-01"), DateUtil.parse("2021-07-09"));
            System.out.println(pointData);
            String result = getPointHourData(driver, "商业二路", DateUtil.parse("2021-01-01 00:00:00"), DateUtil.parse("2021-01-02 23:59:59"));
            System.out.println(result);
        } finally {
            driver.close();
        }
    }

    /**
     * 获取站点时历史数据
     */
    private static String getPointHourData(WebDriver driver, String pointName, Date startDate, Date endDate) {

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Map obj = (Map) jsExecutor.executeScript("return {\n" +
                "\t\tcity:city,\n" +
                "    pointname:'" + pointName + "',\n" +
                "    type:'HOUR',\n" +
                "    startTime:'" + DateUtil.format(startDate, DatePattern.NORM_DATETIME_FORMAT) + "',\n" +
                "    endTime: '" + DateUtil.format(endDate, DatePattern.NORM_DATETIME_FORMAT) + "',\n" +
                "\t}");
        String param = (String) jsExecutor.executeScript("return getParam('GETCITYPOINTPERIOD',arguments[0])", obj);

        HashMap<String, Object> map = MapUtil.newHashMap();
        map.put("param", param);
        String post = HttpUtil.post("https://www.zq12369.com/api/newzhenqiapi.php", map);
        String result = (String) jsExecutor.executeScript(" return b.decode(decryptData('" + post + "'))");
        result = UnicodeUtil.toString(result);
        return result;
    }

    /**
     * 获取站点日历史数据
     */
    private static Map<String, String> getPointDayData(WebDriver driver, Date startDate, Date endDate) {
        Map<String, String> resultMap = MapUtil.newHashMap();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        long between = DateUtil.between(startDate, endDate, DateUnit.DAY);
        for (long i = 0; i <= between; i++) {
            DateTime dateTime = DateUtil.offset(startDate, DateField.DAY_OF_YEAR, (int) i);
            String date = DateUtil.format(dateTime, DatePattern.NORM_DATE_PATTERN);
            String appId = (String) jsExecutor.executeScript("return appId");
            String method = (String) jsExecutor.executeScript("return encode_param('GETCITYPOINTAVG')");
            String city = (String) jsExecutor.executeScript("return encode_param(city)");
            String startTime = (String) jsExecutor.executeScript("return encode_param('" + date + "')");
            String endTime = (String) jsExecutor.executeScript("return encode_param('" + date + "')");
            String secret = (String) jsExecutor.executeScript("return encode_secret('GETCITYPOINTAVG',encode_param(city),'" + date + "','" + date + "')");

            HashMap<String, Object> map = MapUtil.newHashMap();
            map.put("appId", appId);
            map.put("method", method);
            map.put("city", city);
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("secret", secret);
            String post = HttpUtil.post("https://www.zq12369.com/api/zhenqiapi.php", map);
            String result = (String) jsExecutor.executeScript(" return decode_result('" + post + "')");
            result = UnicodeUtil.toString(result);
            resultMap.put(date, result);
        }
        return resultMap;
    }

}
