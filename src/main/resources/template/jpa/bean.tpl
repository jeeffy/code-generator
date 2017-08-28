<#assign importJacksonAnnotation=false />
<#assign importDateTimeFormat=false />
<#list fields as field>
    <#if field.isId >
    <#elseif field.fieldName=='createTime'|| field.fieldName=='updateTime'>
        <#assign importJacksonAnnotation=true />
    <#elseif field.javaType=='Date'>
        <#assign importDateTimeFormat=true />
    </#if>
</#list>
package ${packageName}.bean;

<#if importJacksonAnnotation >
import com.fasterxml.jackson.annotation.*;
</#if>
<#if importDateTimeFormat >
import org.springframework.format.annotation.DateTimeFormat;
</#if>
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "${tableName}")
public class ${classType}{

<#list fields as field>
    <#if field.isId >
    @Id
    @GeneratedValue
    <#elseif field.fieldName=='createTime'|| field.fieldName=='updateTime'>
    @JsonIgnore
    @Transient
    <#elseif field.javaType=='Date'>
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${field.javaType} ${field.fieldName};

</#list>

<#list fields as field>
    public ${field.javaType} get${field.fieldName?cap_first}() {
        return ${field.fieldName};
    }
    public void set${field.fieldName?cap_first}(${field.javaType} ${field.fieldName}) {
        this.${field.fieldName} = ${field.fieldName};
    }
</#list>

    @Override
    public String toString() {
        return "${classType}{" +
        <#list fields as field>
            "${field.fieldName}=" + ${field.fieldName} + ", " +
        </#list>
        '}';
    }
}