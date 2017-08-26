<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.dao.${classType}Dao">

    <#-- content of resultMap -->
    <resultMap id="${className}Map" type="${packageName}.bean.${classType}">
    <#list fields as field>
        <#if field.isId >
        <id property="${field.fieldName}" column="${field.columnName}"/>
        <#else>
        <result property="${field.fieldName}" column="${field.columnName}"/>
        </#if>
    </#list>
    </resultMap>

    <sql id="queryCondition">
        <where>
        <#list fields as field>
            <if test="${field.fieldName} != null and ${field.fieldName} != ''">
                and ${field.columnName} = ${r"#{"}${field.fieldName}}
            </if>
        </#list>

        </where>
    </sql>

    <select id="list" parameterType="map" resultMap="${className}Map">
        SELECT * FROM ${tableName}
        <include refid="queryCondition" />
    </select>

    <select id="get" parameterType="${idType}" resultMap="${className}Map">
        SELECT * FROM ${tableName} WHERE ${map.toUnderscore(id)} = ${r"#{"}${id}}
    </select>

    <insert id="create" parameterType="${packageName}.bean.${classType}">
    <#if dbType=="mysql" >
        <selectKey resultType="int"  order="AFTER" keyProperty="${id}" >
            SELECT LAST_INSERT_ID()
        </selectKey>
    </#if>
        INSERT INTO ${tableName}(
        <#list fields as field>
            ${field.columnName}<#if field?has_next>,</#if>
        </#list>
        )VALUES(
        <#list fields as field>
            ${r"#{"}${field.fieldName}}<#if field?has_next>,</#if>
        </#list>
        )
    </insert>

    <update id="update" parameterType="${packageName}.bean.${classType}">
        UPDATE ${tableName} SET
        <#list fields as field>
            ${field.columnName} = ${r"#{"}${field.fieldName}}<#if field?has_next>,</#if>
        </#list>
        WHERE ${map.toUnderscore(id)} = ${r"#{"}${id}}
    </update>

    <delete id="delete" parameterType="${idType}">
        DELETE FROM ${tableName} WHERE ${map.toUnderscore(id)} = ${r"#{"}${id}}
    </delete>


</mapper>