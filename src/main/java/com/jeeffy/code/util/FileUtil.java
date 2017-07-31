package com.jeeffy.code.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jiangfeng on 2017/7/31.
 */
public class FileUtil {

    public static String read(String path){
        File file = new File(path);
        String str = read(file);
        return str;
    }

    public static String read(File file) {
        StringBuffer res = new StringBuffer();
        String line = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (i != 0) {
                    res.append('\n');
                }
                res.append(line);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toString();
    }
}
