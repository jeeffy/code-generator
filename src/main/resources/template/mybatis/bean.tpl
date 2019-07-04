package ${packageName}.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

@Data
@TableName("${tableName}")
public class ${classType}{

<#list fields as field>
    <#if field.comment??&&field.comment!='' >
    @ApiModelProperty("${field.comment}")
    </#if>
    private ${field.javaType} ${field.fieldName};
</#list>

}