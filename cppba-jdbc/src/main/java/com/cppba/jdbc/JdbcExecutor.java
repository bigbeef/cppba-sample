package com.cppba.jdbc;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcExecutor {
    private static final String username = "";
    private static final String password = "";
    private static final String url = "jdbc:mysql://rm-bp1jff5uy70f5v4dpo.mysql.rds.aliyuncs.com:3306/information_schema?useUnicode=true&characterEncoding=UTF8";

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        HashMap<String, List<String>> allColumn = new HashMap<String, List<String>>();
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            String sql = " SELECT * FROM `COLUMNS` where table_name in ( " +
                    " select table_name from `TABLES` where TABLE_SCHEMA = 'safe_online' " +
                    " ); ";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String tableName = resultSet.getString("table_name");
                String columnName = resultSet.getString("column_name");
                List<String> list = allColumn.get(tableName);
                if (list == null) {
                    list = new ArrayList<String>();
                }
                list.add(columnName);
                allColumn.put(tableName, list);
                // System.out.println(String.format("%s -> %s , %s -> %s", "tableName", tableName, "columnName", columnName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        for (Map.Entry<String, List<String>> entry : allColumn.entrySet()) {
            String tableName = entry.getKey();
            Boolean isFindAddTime = false, isFindUpdateTime = false;
            List<String> valueList = entry.getValue();
            for (String value : valueList) {
                if (StringUtils.equals(value, "raw_add_time")) {
                    isFindAddTime = true;
                } else if (StringUtils.equals(value, "raw_update_time")) {
                    isFindUpdateTime = true;
                }
            }
            String sql = JdbcExecutor.buildSql(tableName, isFindAddTime, isFindUpdateTime);
            if (StringUtils.isNoneBlank(sql)){
                System.out.println(sql);
            }
        }
    }

    public static String buildSql(String tableName, Boolean isFindAddTime, Boolean isFindUpdateTime) {
        if (isFindAddTime && isFindUpdateTime){
            return "";
        }
        String sql = " ALTER TABLE " + tableName;
        if (!isFindAddTime) {
            sql += " ADD raw_add_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录添加时间',";
        }
        if (!isFindUpdateTime) {
            sql += " ADD raw_update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录修改时间',";
        }
        sql = sql.substring(0,sql.length()-1);
        sql += "; ";
        return sql;
    }
}