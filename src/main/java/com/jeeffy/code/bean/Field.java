package com.jeeffy.code.bean;

/**
 * Created by Jeeffy on 2017/8/25.
 */
public class Field {
    private String fieldName;
    private String columnName;
    private String javaType;
    private String jdbcType;
    private String comment;
    private Boolean isId = false;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getIsId() {
        return isId;
    }

    public void setIsId(Boolean isId) {
        this.isId = isId;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldName='" + fieldName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", javaType='" + javaType + '\'' +
                ", jdbcType='" + jdbcType + '\'' +
                ", comment='" + comment + '\'' +
                ", isId=" + isId +
                '}';
    }
}
