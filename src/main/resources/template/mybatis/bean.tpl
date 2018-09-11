package ${packageName}.bean;

import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

@Data
public class ${classType}{

<#list fields as field>
    <#if field.comment??&&field.comment!='' >
    @ApiModelProperty("${field.comment}")
    </#if>
    private ${field.javaType} ${field.fieldName};
</#list>

}