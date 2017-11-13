package ${packageName}.bean;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
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
}