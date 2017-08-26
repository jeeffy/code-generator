package com.jeeffy.code.util;

import com.jeeffy.code.bean.Field;
import com.jeeffy.code.bean.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeeffy on 2017/8/26.
 */
public class ModelBuilder {

    public static Model build(String tableName){
        Model model = new Model();
        model.setPackageName(PropertiesUtil.getPackage());
        model.setTableName(tableName);
        model.setDao(PropertiesUtil.getDao());
        Map<String,String> idMap =  DBUtil.getIdMap(tableName);
        model.setId(idMap.get("id"));
        model.setIdType(idMap.get("idType"));

        List<Field> fields = DBUtil.getFields(tableName);
        model.setFields(fields);

        String className = StringUtil.removePrefix(tableName, PropertiesUtil.getRemovableTablePrefix());
        className = StringUtil.camelCase(className);
        String classType = StringUtil.firstUpperCase(className);

        model.setClassName(className);
        model.setClassType(classType);
        model.setDbType(DBUtil.getDatabaseType());

        Map<String, Object> map = new HashMap<>();
        map.put("toUnderscore", new UnderscoreTemplateMethodModel());
        model.setMap(map);

        return model;
    }

    public static Model build(Map<String, Object> map){
        throw new RuntimeException("don't be implemented");
    }
}
