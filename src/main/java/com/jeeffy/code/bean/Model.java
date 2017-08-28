package com.jeeffy.code.bean;

import com.jeeffy.code.util.FormatTemplateMethodModel;
import freemarker.template.TemplateModel;

import java.util.List;
import java.util.Map;

/**
 * Created by Jeeffy on 2017/8/25.
 */
public class Model {

    private String classType;
    private String className;
    private String tableName;
    private String packageName;
    private List<Field> fields;
    private String dbType;
    private String dao = "jpa";
    private String id;
    private String idType;

    private TemplateModel format = new FormatTemplateMethodModel();

    public TemplateModel getFormat() {
        return format;
    }

    public void setFormat(TemplateModel format) {
        this.format = format;
    }
    public String getDao() {
        return dao;
    }

    public void setDao(String dao) {
        this.dao = dao;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "Model{" +
                "classType='" + classType + '\'' +
                ", className='" + className + '\'' +
                ", tableName='" + tableName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", fields=" + fields +
                ", dbType='" + dbType + '\'' +
                ", dao='" + dao + '\'' +
                ", id='" + id + '\'' +
                ", idType='" + idType + '\'' +
                '}';
    }
}
