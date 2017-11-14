package ${packageName}.bean;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "${tableName}")
public class ${classType}{

<#list fields as field>
    <#if field.isId >
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    <#elseif field.fieldName=='createTime'|| field.fieldName=='updateTime'>
    @JsonIgnore
    @Transient
    </#if>
    <#if field.comment??&&field.comment!='' >
    @ApiModelProperty("${field.comment}")
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
        "}";
    }
}