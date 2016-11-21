<?xml version="1.0" encoding="UTF-8" ?>  
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd"> 
<mapper namespace="${packageName}.dao.${ClassName}Dao">
    <resultMap id="${className}Map" type="${packageName}.bean.${ClassName}">
${resultMap}
    </resultMap>
    
    <sql id="queryCondition">
        <where>
${queryConditionSql}
        </where>
    </sql>
    	
    <select id="getByMap" parameterType="map" resultMap="${className}Map">
    	${selectListSql}
    </select>
    
    <select id="getById" parameterType="${pkType}" resultMap="${className}Map">
    	${selectSql}
    </select>
    
    <insert id="create" parameterType="${packageName}.bean.${ClassName}">
        ${insertPk}
		${insertSql}
    </insert>
    
    <update id="update" parameterType="${packageName}.bean.${ClassName}">
		${updateSql}
    </update>
    
    <delete id="delete" parameterType="${pkType}">
    	${deleteSql}
    </delete>
    
</mapper>