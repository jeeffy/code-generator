package com.jeeffy.code.bean;

/**
 * Created by Jeeffy on 2017/8/25.
 */
public class Field {
    private String name;
    private String column;
    private String javaType;
    private String jdbcType;
    private String comment;
    private Boolean isId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
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
}
